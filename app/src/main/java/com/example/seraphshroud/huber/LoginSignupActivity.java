package com.example.seraphshroud.huber;

/**
 * Created by Alicia on 11/3/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginSignupActivity extends Activity {
    // Declare Variables
    Button loginbutton;
    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.loginsignup);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.client_username);
        password = (EditText) findViewById(R.id.password);

        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        // Login Button Click Listener
        loginbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();

                if(usernametxt.isEmpty() || usernametxt.length() < 6 || usernametxt.contains(" "))
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Must enter valid username to login",
                            Toast.LENGTH_LONG).show();
                }
                else if(passwordtxt.isEmpty() || passwordtxt.length() < 6)
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Not a valid password. Please try again.",
                            Toast.LENGTH_LONG).show();
                }

                // Send data to Parse.com for verification
                ParseUser.logInInBackground(usernametxt, passwordtxt,
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // If user exist and authenticated, send user to Welcome.class
                                    if(user.getBoolean("isBarber") == true) {
                                        Intent intent = new Intent(
                                                LoginSignupActivity.this,
                                                Welcome.class);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(
                                                LoginSignupActivity.this,
                                                ClientWelcome.class);
                                        startActivity(intent);
                                    }
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Logged in",
                                            Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "No such user exist, please signup",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        // Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                //usernametxt = username.getText().toString();
                //passwordtxt = password.getText().toString();
                /*
                if(usernametxt.isEmpty())
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Must enter valid username to sign up",
                            Toast.LENGTH_LONG).show();
                }
                else if(usernametxt.length() < 6 || passwordtxt.length() < 6)
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Username & Password must be at least 6 characters long",
                            Toast.LENGTH_LONG).show();
                }
                else if(usernametxt.contains(" "))
                {
                    Toast.makeText(
                            getApplicationContext(),
                            "Username must not contain any spaces",
                            Toast.LENGTH_LONG).show();
                }
                else if(passwordtxt.isEmpty()) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Not a valid password. Please try again.",
                            Toast.LENGTH_LONG).show();
                }

                // Force user to fill up the form
                if (usernametxt.equals("") && passwordtxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();

                } else {
                    if(usernametxt != null && usernametxt.length() >= 6 && !(usernametxt.contains(" "))) {
                        if (passwordtxt != null && passwordtxt.length() >= 6) {
                            // Save new user data into Parse.com Data Storage
                            ParseUser user = new ParseUser();
                            user.setUsername(usernametxt);
                            user.setPassword(passwordtxt);
                            user.signUpInBackground(new SignUpCallback() {
                                public void done(ParseException e) {
                                    if (e == null) { */
                                        Intent intent = new Intent(
                                                LoginSignupActivity.this,
                                                ClientOrBarber.class);
                                        startActivity(intent);
                                        // Show a simple Toast message upon successful registration
                                /*Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Sign up Error", Toast.LENGTH_LONG)
                                                .show();
                                    }
                                }
                            });
                        }
                    }
                }*/

            }
        });

    }
}