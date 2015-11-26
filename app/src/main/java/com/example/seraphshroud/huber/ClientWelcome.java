package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Alicia on 11/5/2015.
 */
public class ClientWelcome extends Activity {

    // Declare Variable
    Button logout;
    Button findBarber;
    String barberName, day, time, barberPhone;
    boolean confirmed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.client_welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        final ListView listView = (ListView) findViewById(R.id.clientList);
        listView.setAdapter(listAdapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointments");
        query.whereEqualTo("client", currentUser.getString("name"));
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    ParseObject u = objects.get(i);
                    confirmed = u.getBoolean("confirmed");
                    barberName = u.getString("barber");
                    barberPhone = u.getString("barberPhone");
                    day = u.getString("aptDay");
                    time = u.getString("aptTime");
                    if(confirmed) {
                        listAdapter.add("Your barber " + barberName + " has confirmed your appointment on " + day + " " + time +
                                        ". \nPlease contact your barber: " + barberPhone);
                    } else {
                        listAdapter.add("We're sorry. Your barber " + barberName + " has declined your request appointment on " + day + " " + time);
                    }

                }
            }

        });

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        findBarber = (Button) findViewById(R.id.findBarber);

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

