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
public class BarberInfo extends Activity {

    Button nextbutton;
    EditText username, password;
    EditText email;
    EditText phone;
    EditText location;
    EditText specialty;
    EditText priceRange;
    String usernametxt, passwordtxt;
    String emailtxt;
    String phonetxt;
    String locationtxt;
    String specialtytxt;
    String priceRangetxt;

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_info);

        nextbutton = (Button) findViewById(R.id.next);

        username = (EditText) findViewById(R.id.barber_username);
        password = (EditText) findViewById(R.id.barber_password);
        email = (EditText) findViewById(R.id.barber_email);
        phone = (EditText) findViewById(R.id.barber_phone);
        location = (EditText) findViewById(R.id.barber_location);
        specialty = (EditText) findViewById(R.id.barber_specialty);
        priceRange = (EditText) findViewById(R.id.barber_price);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve current user from Parse.com
                ParseUser user = new ParseUser();

                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
                emailtxt = email.getText().toString();
                phonetxt = phone.getText().toString();
                locationtxt = location.getText().toString();
                specialtytxt = specialty.getText().toString();
                priceRangetxt = priceRange.getText().toString();

                if(phonetxt.length() != 13 || !(phonetxt.contains("(")) || !
                        (phonetxt.contains(")")) || !(phonetxt.contains("-"))) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter a valid phone number to sign up ex. (123)456-7890",
                            Toast.LENGTH_LONG).show();
                }

                else if(!(isNumeric(priceRangetxt))) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter a valid price range ex. 5.50",
                            Toast.LENGTH_LONG).show();
                }

                // Insert the user's information into the database
                user.setUsername(usernametxt);
                user.setPassword(passwordtxt);
                user.setEmail(emailtxt);
                user.put("phoneNumber", phonetxt);
                user.put("location", locationtxt);
                user.put("specialty", specialtytxt);
                user.put("priceRange", priceRangetxt);
                user.put("isBarber", true);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Once finished, go to welcome page
                            Intent intent = new Intent(
                                    BarberInfo.this, Calendar.class);
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