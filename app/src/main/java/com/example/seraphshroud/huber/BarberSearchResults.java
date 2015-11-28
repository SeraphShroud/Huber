package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Brian on 11/10/2015.
 */
public class BarberSearchResults extends Activity {

    final static int DAYS_IN_WEEK = 7;
    String[] schedule = new String[DAYS_IN_WEEK];
    String day;
    int startTime, endTime;
    int low, high;
    int pos;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_search_results);

        final DecimalFormat df = new DecimalFormat("#.00");

        // Grab the price and time from FindBarber.java
        Bundle b = getIntent().getExtras();
        day = b.getString("day");
        startTime = b.getInt("startTime");
        endTime = b.getInt("endTime");
        low = b.getInt("low");
        high = b.getInt("high");
        pos = b.getInt("dayPos");

        /*System.out.println("Start time is: " + startTime + "\n End time is: " + endTime);
        System.out.println("Day is: " + day + ", dayPos is: " + pos);
        System.out.println("Low price is: " + low + "\n High price is: " + high);*/
/*

        // Create the Query adapter and search for only barbers
        ParseQueryAdapter<ParseUser> userAdapter =
                new ParseQueryAdapter<ParseUser>(this, new ParseQueryAdapter.QueryFactory<ParseUser>() {
                    public ParseQuery<ParseUser> create() {
                        // Here we can configure a ParseQuery to our heart's desire.

                        ParseQuery query = ParseUser.getQuery();
                        query.orderByAscending("price");
                        query.whereEqualTo("isBarber", true);
                        query.whereGreaterThanOrEqualTo("price", low);
                        query.whereLessThanOrEqualTo("price", high);
                        return query;
                    }
                });
        userAdapter.setTextKey("name");
        userAdapter.setTextKey("price");
        userAdapter.setTextKey("location");
        ListView listView = (ListView) findViewById(R.id.barberList);
        listView.setAdapter(userAdapter);*/

        // Create new arrays to store barber's name, location, price, and specialty
        final ArrayList<String> barberId = new ArrayList<String>();
        final ArrayList<String> barberNames = new ArrayList<String>();
        final ArrayList<String> barberLocation = new ArrayList<String>();
        final ArrayList<Double> barberPrice = new ArrayList<Double>();
        final ArrayList<String> barberAvailability = new ArrayList<String>();
        final ArrayList<String> barberSpecialty = new ArrayList<String>();

        // Populates the barber search results list
//        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
//                R.layout.mylist);

        final ListView listView = (ListView)findViewById(R.id.barberList);
        final CustomListAdapter listAdapter = new CustomListAdapter(this, barberNames, barberLocation, barberPrice, barberAvailability);
        //listView.setAdapter(listAdapter);

        // Check the database for Users
        final ParseQuery query = ParseUser.getQuery();

        query.orderByAscending("price");
        query.whereEqualTo("isBarber", true);
        query.whereGreaterThanOrEqualTo("price", low);
        query.whereLessThanOrEqualTo("price", high);
        query.whereExists("schedule");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {

                    // Gets all the barber's name, location, and price and add to array
                    for (int i = 0; i < objects.size(); i++) {
                        ParseUser u = (ParseUser) objects.get(i);

                        // Create an array to store the array from parse since you cannot check 1 element in an array from parse
                        List<String> barberSchedule;
                        barberSchedule = u.getList("schedule");
                        barberSchedule.toArray(schedule);
                        // pos is from 0-6 being Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
                        String availability = schedule[pos];

                        // Format is in two 24 hour times, so split it into a start and end time
                        String[] times = availability.split(" ");
                        if (times.length != 2) {
                            throw new IllegalArgumentException();
                        }
                        int start = Integer.parseInt(times[0]);
                        int end = Integer.parseInt(times[1]);

                        /*Date d1, d2;

                        // Format the 24 hour clock to 12 hour for readability
                        d1 = new SimpleDateFormat("hhmm").parse(String.format("%04d", times[0]));
                        d2 = new SimpleDateFormat("hhmm").parse(String.format("%04d", times[1]));

                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mma"); sdf.format(d1)*/

                        // Only display the barbers that fit within the schedule chosen
                        if (startTime <= start && endTime >= end) {
                            String bId = u.getObjectId();
                            String name = u.getString("name");
                            String location = u.getString("location");
                            double priceTxt = u.getDouble("price");
                            String specTxt = u.getString("specialty");

                            barberId.add(bId);
                            barberNames.add(name);
                            barberLocation.add(location);
                            barberPrice.add(priceTxt);
                            barberSpecialty.add(specTxt);
                            barberAvailability.add(availability);


                            listView.setAdapter(listAdapter);

                            // Tabs sometimes don't show on phones
                            //listAdapter.add("Barber:\t\t" + name + "\n" + "Location:\t" + location + "\n" + "Price:\t\t\t" + "$" + df.format(priceTxt) + "\n" +
                              //      "Available on " + day + " from " + times[0] + " to " + times[1]);

                        }
                    }
                }
            }
        });

//        final ListView listView = (ListView)findViewById(R.id.barberList);
//        CustomListAdapter listAdapter = new CustomListAdapter(this, barberNames, barberLocation, barberPrice, barberAvailability);
//        listView.setAdapter(listAdapter);

            // Clicking on a certain item will redirect to profile page
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> a, View v, int position,
                                                long id) {

                            String itemId = barberId.get(position);
                            String itemName = barberNames.get(position);
                            String itemLoc = barberLocation.get(position);
                            double itemPrice = barberPrice.get(position);
                            String itemSpec = barberSpecialty.get(position);
                            String itemSchedule = barberAvailability.get(position);


                            Intent intent = new Intent(BarberSearchResults.this, BarberProfile.class);

                            // Pass all the barber's information to be used in profile page
                            intent.putExtra("id", itemId);
                            intent.putExtra("name", itemName);
                            intent.putExtra("location", itemLoc);
                            intent.putExtra("price", itemPrice);
                            intent.putExtra("specialty", itemSpec);
                            intent.putExtra("schedule", itemSchedule);
                            intent.putExtra("day", day);
                            startActivity(intent);
                        }
                    }

            );
        }
}