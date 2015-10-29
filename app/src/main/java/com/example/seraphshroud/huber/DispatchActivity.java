package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.parse.ParseUser;

/**
 * Created by SeraphShroud on 10/29/2015.
 */
public class DispatchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Check if there is current user info
        if (ParseUser.getCurrentUser() != null ) {
            //Start an intent for the login in activity
            startActivity(new Intent(this, MainActivity.class));
        }
        else {
            startActivity(new Intent(this, SignUpOrLoginActivity.class));
        }
    }
}
