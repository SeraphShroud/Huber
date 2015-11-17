package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by SeraphShroud on 11/3/2015.
 */
public class ClientInfo extends Activity {

    Button donebutton;
    EditText email;
    EditText phone;
    EditText location;
    //EditText name;
    EditText username, password;
    String usernametxt, passwordtxt;
    //String name; Anything with name in it will not work in the database
    String emailtxt;
    String phonetxt;
    String locationtxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.client_info);

        donebutton = (Button) findViewById(R.id.done);

        username = (EditText) findViewById(R.id.client_username);
        password = (EditText) findViewById(R.id.client_password);
        email = (EditText) findViewById(R.id.client_email);
        //firstName = (EditText) findViewById(R.id.client_firstName);
        //lastName = (EditText) findViewById(R.id.client_lastName);
        phone = (EditText) findViewById(R.id.client_phone);
        location = (EditText) findViewById(R.id.client_location);

        donebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve current user from Parse.com
                ParseUser user = new ParseUser();

                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                emailtxt = email.getText().toString();
                //firstNametxt = firstName.getText().toString();
                //lastNametxt = lastName.getText().toString();
                phonetxt = phone.getText().toString();
                locationtxt = location.getText().toString();

                // Insert the user's information into the database
                user.setUsername(usernametxt);
                user.setPassword(passwordtxt);
                user.setEmail(emailtxt);
                //user.put("firstName,", firstNametxt);
                //user.put("lastName,", lastNametxt);
                user.put("phoneNumber", phonetxt);
                user.put("location", locationtxt);
                user.put("isBarber", false);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Once finished, go to welcome page
                            Intent intent = new Intent(
                                    ClientInfo.this, ClientWelcome.class);
                            startActivity(intent);
                            // Show a simple Toast message upon successful registration
                                /*Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();*/
                        } else {
                            int error = e.getCode();
                            System.out.println(error);
                            Toast.makeText(getApplicationContext(),
                                    "Sign up Error", Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });

            }

        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);
        });
    }
}