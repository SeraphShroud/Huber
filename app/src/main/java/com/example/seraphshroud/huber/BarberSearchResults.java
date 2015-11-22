package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brian on 11/10/2015.
 */
public class BarberSearchResults extends Activity {

    String price, time, name;
    int low, high;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_search_results);

        // Grab the price and time from FindBarber.java
        Bundle b = getIntent().getExtras();
        price = b.getString("price");
        time = b.getString("time");

        // Adjust lower and upper bounds based on user's price
        if (price == "$0-$10") {
            low = 0;
            high = 10;
        }
        else if (price == "$10-$15") {
            low = 10;
            high = 15;
        }
        else if (price == "$15-$20") {
            low = 15;
            high = 20;
        }
        else if (price == "$20-$25") {
            low = 20;
            high = 25;
        }
        else {
            low = 0;
            high = 100;
        }

        System.out.println("Price is: " + price);
        System.out.println("Time is: " + time);

//        // Create the Query adapter and search for only barbers
//        ParseQueryAdapter<ParseUser> userAdapter =
//                new ParseQueryAdapter<ParseUser>(this, new ParseQueryAdapter.QueryFactory<ParseUser>() {
//                    public ParseQuery<ParseUser> create() {
//                        // Here we can configure a ParseQuery to our heart's desire.
//
//                        ParseQuery query = ParseUser.getQuery();
//                        //query.whereContainedIn("isBarber", Arrays.asList({"priceRange"}));
//                        //query.whereGreaterThanOrEqualTo("priceRange", low);
//                        //query.whereLessThanOrEqualTo("priceRange", high);
//                        query.orderByAscending("name");
//                        query.whereEqualTo("isBarber", true);
//                        return query;
//                    }
//                });
//        userAdapter.setTextKey("name");
//        userAdapter.setTextKey("price");
//        userAdapter.setTextKey("location");
//
//        ListView listView = (ListView) findViewById(R.id.barberList);
//        listView.setAdapter(userAdapter);

        // Create new arrays to store barber's name, location, price, and specialty
        final ArrayList<String> barberNames = new ArrayList<String>();
        final ArrayList<String> barberLocation = new ArrayList<String>();
        final ArrayList<Integer> barberPrice = new ArrayList<Integer>();
        final ArrayList<String> barberSpecialty = new ArrayList<String>();


        //Populates the barber search results list
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        final ListView listView = (ListView)findViewById(R.id.barberList);
        listView.setAdapter(listAdapter);
        final ParseQuery query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {

                    // Gets all the barber's name, location, and price and add to array
                    for (int i = 0; i < objects.size(); i++) {
                        ParseUser u = (ParseUser) objects.get(i);
                        String name = u.getString("name");
                        String location = u.getString("location");
                        int priceTxt = u.getInt("price");
                        //String specTxt = u.getString("specialty");


                        barberNames.add(name);
                        barberLocation.add(location);
                        barberPrice.add(priceTxt);
                        //barberSpecialty.add(specTxt);

                        listAdapter.add("Barber:       " + name + "\n" + "Location:   " + location + "\n" + "Price:          " + priceTxt);
                    }
                }

            }
        });

        // Clicking on a certain item will redirect to profile page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                String itemName = barberNames.get(position);
                String itemLoc = barberLocation.get(position);
                String itemPrice = barberPrice.get(position).toString();
                //String itemSpec = barberSpecialty.get(position);

                Intent intent = new Intent(BarberSearchResults.this, BarberProfile.class);

                // Pass all the barber's information to be used in profile page
                intent.putExtra("name", itemName);
                intent.putExtra("location", itemLoc);
                intent.putExtra("price", itemPrice);
//                intent.putExtra("specialty", itemSpec);
                startActivity(intent);
            }
        });
    }
}