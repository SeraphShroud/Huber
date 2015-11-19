package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Arrays;

/**
 * Created by Brian on 11/10/2015.
 */
public class BarberSearchResults extends Activity {

    String price, time;
    int low, high;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.barber_search_results);

        // Grab the price and time from FindBarber.java
        Bundle b = getIntent().getExtras();
        price = b.getString("price");
        time = b.getString("time");

        // Adjust lower and upper bounds based on user's price
        if (price == "$0-$10") {
            low = 0;
            high = 10;
        }
        else if (price == "$10-$15") {
            low = 10;
            high = 15;
        }
        else if (price == "$15-$20") {
            low = 15;
            high = 20;
        }
        else if (price == "$20-$25") {
            low = 20;
            high = 25;
        }
        else {
            low = 0;
            high = 100;
        }

        System.out.println("Price is: " + price);
        System.out.println("Time is: " + time);

        // Create the Query adapter and search for only barbers
        ParseQueryAdapter<ParseUser> userAdapter =
                new ParseQueryAdapter<ParseUser>(this, new ParseQueryAdapter.QueryFactory<ParseUser>() {
                    public ParseQuery<ParseUser> create() {
                        // Here we can configure a ParseQuery to our heart's desire.

                        ParseQuery query = ParseUser.getQuery();
                        //query.whereContainedIn("isBarber", Arrays.asList({"priceRange"}));
                        //query.whereGreaterThanOrEqualTo("priceRange", low);
                        //query.whereLessThanOrEqualTo("priceRange", high);
                        query.orderByAscending("name");
                        query.whereEqualTo("isBarber", true);
                        return query;
                    }
                });
        userAdapter.setTextKey("name");

        ListView listView = (ListView) findViewById(R.id.barberList);
        listView.setAdapter(userAdapter);

        // Clicking on a certain item will redirect to profile page
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {

                Intent intent = new Intent(BarberSearchResults.this, BarberProfile.class);

                startActivity(intent);
            }
        });
    }
}