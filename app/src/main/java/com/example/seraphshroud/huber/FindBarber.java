package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Alicia on 11/10/2015.
 */
public class FindBarber extends Activity {

    String pricetxt, timetxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.find_barber);

        // Create the spinner for prices
        final Spinner priceDropdown = (Spinner) findViewById(R.id.price_range);

        // Allow for spinner to select prices from the drop down menu
        priceDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String price = priceDropdown.getSelectedItem().toString();
                parent.getItemAtPosition(pos);
                Log.i("Selected item : ", price);
                pricetxt = price;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Empty because we must have something selected
            }

        });
        String[] priceItems = new String[]{"$0-$10", "$10-$15", "$15-$20", "$20-$25"};

        // Create the spinner for times
        final Spinner timeDropdown = (Spinner) findViewById(R.id.time_range);

        // Allow for spinner to select times from the drop down menu
        timeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String time = timeDropdown.getSelectedItem().toString();
                parent.getItemAtPosition(pos);
                Log.i("Selected item : ", time);
                timetxt = time;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Empty because we must have something selected
            }

        });
        String[] timeItems = new String[]{"8:00am - 11:00am", "11:00am - 2:00pm", "2:00pm - 5:00pm"};

        Button searchbtn = (Button) findViewById(R.id.searchbtn);

        // Create the arrays for the drop down menu for prices
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, priceItems);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceDropdown.setAdapter(priceAdapter);

        // Create the arrays for the drop down menu for times
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, timeItems);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(timeAdapter);

        // Once the search button is selected, go to the BarberSearchResults page
        searchbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You selected price : " + pricetxt + "\n @ " + timetxt, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        FindBarber.this,
                        BarberSearchResults.class);

                // Pass in the price and time to the BarberSearchResults page
                Bundle b = new Bundle();
                b.putString("price", pricetxt);
                b.putString("time", timetxt);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
