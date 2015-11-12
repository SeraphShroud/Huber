package com.example.seraphshroud.huber;

/**
 * Created by Alicia on 11/3/2015.
 */
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends Activity {

    // Declare Variable
    Button logout;
    View calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);

        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();

        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);

        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);

        // Logout Button Click Listener
        logout.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
                Intent intent = new Intent(
                        Welcome.this,
                        LoginSignupActivity.class);
                startActivity(intent);
            }
        });

        calendar = (Button) findViewById(R.id.calendar);

        calendar.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                startActivity(new Intent(Welcome.this, Calendar.class));
            }
        });
    }
}
