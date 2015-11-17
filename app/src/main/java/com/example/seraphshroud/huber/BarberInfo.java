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
    EditText name;
    EditText location;
    EditText specialty;
    EditText priceRange;
    String usernametxt, passwordtxt;
    String nametxt = null;
    String emailtxt = null;
    String phonetxt = null;
    String locationtxt = null;
    String specialtytxt = null;
    String priceRangetxt = null;
    int inputError = 0;

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
        name = (EditText) findViewById(R.id.barber_name);
        phone = (EditText) findViewById(R.id.barber_phone);
        location = (EditText) findViewById(R.id.barber_location);
        specialty = (EditText) findViewById(R.id.barber_specialty);
        priceRange = (EditText) findViewById(R.id.barber_price);

        nextbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Retrieve the text entered from the EditText and check if fields were entered
                if (username != null) {
                    usernametxt = username.getText().toString();
                } else { inputError = 1; }

                if (password != null) {
                    passwordtxt = password.getText().toString();
                } else { inputError = 2; }

                if (email != null) {
                    emailtxt = email.getText().toString();
                } else { inputError = 3; }

                if (name != null) {
                    nametxt = name.getText().toString();
                } else { inputError = 4; }

                if ( phone!= null) {
                    phonetxt = phone.getText().toString();
                } else { inputError = 5; }

                if ( location!= null) {
                    locationtxt = location.getText().toString();
                } else { inputError = 6; }

                if ( specialty!= null) {
                    specialtytxt = specialty.getText().toString();
                } else { inputError = 7; }

                if ( priceRange!= null) {
                    priceRangetxt = priceRange.getText().toString();
                } else { inputError = 8; }

                if (inputError != 0) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Please fill out all the fields.",
                            Toast.LENGTH_SHORT).show();
                    System.out.println("Error code: " + inputError);
                }
                // TODO This verification doesn't work even if it's right. Please correct this
                /*else if (phonetxt.length() != 13 || !(phonetxt.contains("(")) || !
                        (phonetxt.contains(")")) || !(phonetxt.contains("-"))) {
                    inputError = 5;
                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter a valid phone number to sign up ex. (123)456-7890",
                            Toast.LENGTH_LONG).show();
                }*/ else if (!(isNumeric(priceRangetxt))) {
                    inputError = 8;
                    Toast.makeText(
                            getApplicationContext(),
                            "Please enter a valid price range ex. 5.50",
                            Toast.LENGTH_LONG).show();
                }

                // If no errors, create a new barber user
                if (inputError == 0) {
                    ParseUser user = new ParseUser();

                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.setEmail(emailtxt);

                    // Extra check if the user inputted a name
                    if (nametxt.length() != 0) {
                        user.put("name", nametxt);
                    }
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
                                switch (e.getCode()) {
                                    case ParseException.INVALID_EMAIL_ADDRESS:
                                        Toast.makeText(getApplicationContext(),
                                                com.parse.ui.R.string.com_parse_ui_invalid_email_toast, Toast.LENGTH_SHORT)
                                                .show();
                                        break;
                                    case ParseException.USERNAME_TAKEN:
                                        Toast.makeText(getApplicationContext(),
                                                com.parse.ui.R.string.com_parse_ui_username_taken_toast, Toast.LENGTH_SHORT)
                                                .show();
                                        break;
                                    case ParseException.EMAIL_TAKEN:
                                        Toast.makeText(getApplicationContext(),
                                                com.parse.ui.R.string.com_parse_ui_email_taken_toast, Toast.LENGTH_SHORT)
                                                .show();
                                        break;
                                    default:
                                        Toast.makeText(getApplicationContext(),
                                                com.parse.ui.R.string.com_parse_ui_signup_failed_unknown_toast, Toast.LENGTH_SHORT)
                                                .show();
                                }
                                // Reset the error check
                                inputError = 0;
                            }
                        }
                    });


                }
            }

            // Locate TextView in welcome.xml
            TextView txtuser = (TextView) findViewById(R.id.txtuser);
        });
    }
}