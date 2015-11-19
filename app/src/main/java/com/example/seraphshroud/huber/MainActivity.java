package com.example.seraphshroud.huber;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.ParseAnonymousUtils;
import com.parse.SignUpCallback;
import com.parse.ui.ParseLoginBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /* COMMENT THIS OUT IF YOU WANT TO USE OLD LOGIN SCREEN */
        /*ParseUser currentUser = ParseUser.getCurrentUser();
        System.out.println("USER: " + currentUser);
        if (currentUser == null) {
            ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            startActivityForResult(builder.build(), 0);
        } else {
            Intent launchMainActivity = new Intent(MainActivity.this, ClientOrBarber.class);
            startActivity(launchMainActivity );
            //ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
            //startActivityForResult(builder.build(), 0);
        }*/

        // ************* UNCOMMENT THIS TO USE OLD LOGIN SCREEN ************

        // Determine whether the current user is an anonymous use
        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
            // If user is anonymous, send the user to LoginSignupActivity.class
            Intent intent = new Intent(MainActivity.this,
                    LoginSignupActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // If logged in as Client, go to ClientWelcome.class
                if (!currentUser.getBoolean("isBarber")) {
                    Intent intent = new Intent(MainActivity.this, ClientWelcome.class);
                    startActivity(intent);
                    finish();
                }
                // If logged in as Barber, go to BarberWelcome.class
                else {
                    Intent intent = new Intent(MainActivity.this, BarberWelcome.class);
                    startActivity(intent);
                    finish();
                }
            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(MainActivity.this,
                        LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
