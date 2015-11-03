package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by SeraphShroud on 11/3/2015.
 */
public class ClientInfo extends Activity {

    Button donebutton;
    EditText email;
    EditText phone;
    EditText location;
    String emailtxt;
    String phonetxt;
    String locationtxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.clientinfo);

        donebutton = (Button) findViewById(R.id.done);

        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        location = (EditText) findViewById(R.id.location);

        donebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve current user from Parse.com
                ParseUser currentUser = ParseUser.getCurrentUser();

                // Retrieve the text entered from the EditText
                emailtxt = email.getText().toString();
                phonetxt = phone.getText().toString();
                locationtxt = location.getText().toString();

                // Insert the user's information into the database
                currentUser.setEmail(emailtxt);
                currentUser.put("location", locationtxt);
                currentUser.put("phoneNumber", phonetxt);
                currentUser.saveInBackground();
                /*System.out.println("emailtxt" + emailtxt);
                System.out.println("phonetxt" + emailtxt);
                System.out.println("locationtxt" + emailtxt);*/
                // Once finished, go to welcome page
                Intent intent = new Intent(
                        ClientInfo.this, Welcome.class);
                startActivity(intent);
            }

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);

        });
    }
}