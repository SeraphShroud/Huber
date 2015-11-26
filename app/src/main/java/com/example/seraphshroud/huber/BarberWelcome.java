package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alicia on 11/5/2015.
 */
public class BarberWelcome extends Activity {

    // Declare Variable
    Button logout;
    View calendar;
    String clientName, day, time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        // Show the message of barbers
        //final TextView message = (TextView) findViewById(R.id.messages);

        //ParseUser currentUser = ParseUser.getCurrentUser();
        System.err.println("current: " + currentUser.getObjectId());

        final ArrayList<String> barberMessages = new ArrayList<String>();

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        final ListView listView = (ListView) findViewById(R.id.messageList);
        listView.setAdapter(listAdapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseMessage");
        query.whereEqualTo("receiver", currentUser.getObjectId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    ParseObject u = objects.get(i);
                    String bMessage = u.getString("message");
                    clientName = u.getString("clientName");
                    day = u.getString("aptDay");
                    time = u.getString("aptTime");
                    //barberMessages.add(bMessage);
                    listAdapter.add("Client: " + clientName + "\nMessage: " + bMessage + "\nOn: " + day + " " + time);

                }
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LayoutInflater lay = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = lay.inflate(R.layout.confirm_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Button acceptBtn = (Button) popupView.findViewById(R.id.accept);
                Button rejectBtn = (Button) popupView.findViewById(R.id.reject);
                acceptBtn.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
                        final ParseUser currentUser = ParseUser.getCurrentUser();
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> aptList, com.parse.ParseException e) {
                                if (e == null) {
                                    if (aptList.size() == 0) {
                                        ParseObject appointments = new ParseObject("Appointments");
                                        appointments.put("barber", currentUser.get("name"));
                                        appointments.put("client", clientName);
                                        appointments.put("confirmed",true);
                                        appointments.put("aptDay", day);
                                        appointments.put("aptTime", time);
                                        appointments.put("barberPhone", currentUser.get("phoneNumber"));
                                        appointments.saveInBackground();
                                    }
                                }
                            }
                        });
                        popupWindow.dismiss();
                    }
                });

                rejectBtn.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointment");
                        final ParseUser currentUser = ParseUser.getCurrentUser();
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> aptList, com.parse.ParseException e) {
                                if (e == null) {
                                    if (aptList.size() == 0) {
                                        ParseObject appt = new ParseObject("Appointments");
                                        appt.put("barber", currentUser.get("name"));
                                        appt.put("client", clientName);
                                        appt.put("confirmed", false);
                                        appt.put("aptDay", day);
                                        appt.put("aptTime", time);
                                        appt.saveInBackground();
                                    }
                                }
                            }
                        });
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAsDropDown(findViewById(R.id.calendar), Gravity.LEFT, Gravity.TOP);
            }
        });

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
                Intent intent = new Intent(BarberWelcome.this, LoginSignupActivity.class);
                startActivity(intent);
            }
        });

        calendar = (Button) findViewById(R.id.calendar);

        calendar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                startActivity(new Intent(BarberWelcome.this, Calendar.class));
            }
        });
    }
}