package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alicia on 11/5/2015.
 */
public class ClientWelcome extends Activity {

    // Declare Variable
    Button logout;
    Button findBarber;
    String name, day, time, phone;
    Date date;
    boolean confirmed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the view from singleitemview.xml
        setContentView(R.layout.client_welcome);

        // Displays User's name
        ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getString("name");
        TextView txtuser = (TextView) findViewById(R.id.clientName);
        txtuser.setText(struser);

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);
        findBarber = (Button) findViewById(R.id.findBarber);


        // Create new arrays to store barber's name, location, price, and specialty
        final ArrayList<String> barberName = new ArrayList<String>();
        final ArrayList<String> barberPhone = new ArrayList<String>();
        final ArrayList<String> barberDay = new ArrayList<>();
        final ArrayList<String> barberTime = new ArrayList<String>();
        final ArrayList<String> barberConfirm = new ArrayList<>();
        final ArrayList<Date> barberDate = new ArrayList<>();

        final ApptListAdapter listAdapter = new ApptListAdapter(this, barberName, barberPhone,
                                                                barberDay, barberTime, barberConfirm, barberDate);

        final ListView listView = (ListView) findViewById(R.id.clientList);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseAppointments");
        query.whereEqualTo("client", currentUser.getString("name"));
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    ParseObject u = objects.get(i);
                    confirmed = u.getBoolean("confirmed");
                    name = u.getString("barber");
                    phone = u.getString("barberPhone");
                    day = u.getString("aptDay");
                    time = u.getString("aptTime");
                    date = u.getCreatedAt();

                    barberName.add(name);
                    barberPhone.add(phone);
                    barberDay.add(day);
                    barberTime.add(time);
                    barberDate.add(date);

                    if(confirmed) {
                        barberConfirm.add("Accepted");
                    } else {
                        barberConfirm.add("Declined");
                    }

                    listView.setAdapter(listAdapter);

                }
            }

        });

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
                Intent intent = new Intent(
                        ClientWelcome.this,
                        LoginSignupActivity.class);
                startActivity(intent);
            }
        });

        findBarber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent intent = new Intent(
                        ClientWelcome.this,
                        FindBarber.class);
                startActivity(intent);
            }
        });
    }
}

