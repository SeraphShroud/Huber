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
import android.widget.ImageView;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Alicia on 11/5/2015.
 */
public class BarberWelcome extends Activity {

    // Declare Variable
    Button logout;
    View calendar;
    String name, day, time;
    Date createdAt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_welcome);

        // Display User's name
        ParseUser currentUser = ParseUser.getCurrentUser();
        String struser = currentUser.getUsername().toString();
        TextView txtuser = (TextView) findViewById(R.id.barberName);
        txtuser.setText(struser);

        // Create new arrays to store barber's name, location, price, and specialty
        final ArrayList<String> clientName = new ArrayList<String>();
        final ArrayList<String> clientDay = new ArrayList<>();
        final ArrayList<String> clientTime = new ArrayList<String>();
        final ArrayList<String> clientMessage = new ArrayList<>();
        final ArrayList<Date> clientCreatedAt = new ArrayList<>();

        final MessageListAdapter listAdapter = new MessageListAdapter(this, clientName, clientDay,
                                                                clientTime, clientMessage, clientCreatedAt);

        final ListView listView = (ListView) findViewById(R.id.messageList);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("ParseMessage");
        query.whereEqualTo("receiver", currentUser.getObjectId());
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, com.parse.ParseException e) {
                for (int i = 0; i < objects.size(); i++) {
                    ParseObject u = objects.get(i);
                    String bMessage = u.getString("message");
                    name = u.getString("clientName");
                    day = u.getString("aptDay");
                    time = u.getString("aptTime");
                    createdAt = u.getCreatedAt();


                    clientName.add(name);
                    clientDay.add(day);
                    clientTime.add(time);
                    clientMessage.add(bMessage);
                    clientCreatedAt.add(createdAt);

                    listView.setAdapter(listAdapter);
                }
            }

        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                LayoutInflater lay = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = lay.inflate(R.layout.confirm_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Button acceptBtn = (Button) popupView.findViewById(R.id.accept);
                Button rejectBtn = (Button) popupView.findViewById(R.id.reject);
                ImageView closeBtn = (ImageView) popupView.findViewById(R.id.closeBtn);

                closeBtn.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                acceptBtn.setOnClickListener(new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Appointments");
                        final ParseUser currentUser = ParseUser.getCurrentUser();
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> aptList, com.parse.ParseException e) {
                                if (e == null) {
                                    System.err.println("asss");
                                    //if (aptList.size() == 0) {
                                        System.err.println("2asss");
                                        ParseObject appointments = new ParseObject("ParseAppointments");
                                        appointments.put("barber", currentUser.get("name"));
                                        appointments.put("client", clientName.get(position));
                                        appointments.put("confirmed",true);
                                        appointments.put("aptDay", day);
                                        appointments.put("aptTime", time);
                                        appointments.put("barberPhone", currentUser.get("phoneNumber"));
                                        appointments.saveInBackground();
                                    //}
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
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
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