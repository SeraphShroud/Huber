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
public class BarberInfo extends Activity {

    Button donebutton;
    EditText email;
    EditText phone;
    EditText location;
    EditText gender;
    EditText specialty;
    EditText pricerange;
    EditText name;
    String emailtxt;
    String phonetxt;
    String locationtxt;
    String gendertxt;
    String specialtytxt;
    String pricerangetxt;
    String nametxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_info);

        donebutton = (Button) findViewById(R.id.done);

        gender = (EditText) findViewById(R.id.gender);
        specialty = (EditText) findViewById(R.id.specialty);
        pricerange = (EditText) findViewById(R.id.pricerange);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        location = (EditText) findViewById(R.id.location);

        donebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve current user from Parse.com
                ParseUser currentUser = ParseUser.getCurrentUser();

                // Retrieve the text entered from the EditText
                nametxt = name.getText().toString();
                gendertxt = gender.getText().toString();
                specialtytxt = specialty.getText().toString();
                pricerangetxt = pricerange.getText().toString();
                emailtxt = email.getText().toString();
                phonetxt = phone.getText().toString();
                locationtxt = location.getText().toString();

                // Insert the user's information into the database
                currentUser.setEmail(emailtxt);
                currentUser.put("location", locationtxt);
                currentUser.put("name", nametxt);
                currentUser.put("gender", gendertxt);
                currentUser.put("pricerange", pricerangetxt);
                currentUser.put("specialty", specialtytxt);
                currentUser.put("phoneNumber", phonetxt);
                currentUser.saveInBackground();
                /*System.out.println("emailtxt" + emailtxt);
                System.out.println("phonetxt" + emailtxt);
                System.out.println("locationtxt" + emailtxt);*/
                // Once finished, go to welcome page
                Intent intent = new Intent(
                        BarberInfo.this, Welcome.class);
                startActivity(intent);
            }

            // Locate TextView in welcome.xml
            TextView txtuser = (TextView) findViewById(R.id.txtuser);

        });
    }
}