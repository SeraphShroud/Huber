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

    String priceTxt, timeTxt, dayTxt;
    int startTime, endTime;
    int low, high;
    int dayPos;

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
                priceTxt = price;
                switch (pos) {
                    case 0: low = 0;
                            high = 10;
                        break;
                    case 1: low = 10;
                            high = 15;
                        break;
                    case 2: low = 15;
                            high = 20;
                        break;
                    case 3: low = 20;
                            high = 25;
                        break;
                    default: low = 0;
                            high = 100;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Empty because we must have something selected
            }

        });
        String[] priceItems = new String[]{"$0 - $10", "$10 - $15", "$15 - $20", "$20 - $25"};

        // Create the spinner for days
        final Spinner dayDropdown = (Spinner) findViewById(R.id.day_range);

        // Allow for spinner to select day from the drop down menu
        dayDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String day = dayDropdown.getSelectedItem().toString();
                parent.getItemAtPosition(pos);
                Log.i("Selected item : ", day);
                dayTxt = day;
                dayPos = pos;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Empty because we must have something selected
            }

        });

        String[] dayItems = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        // Create the spinner for times
        final Spinner timeDropdown = (Spinner) findViewById(R.id.time_range);

        // Allow for spinner to select times from the drop down menu
        timeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                String time = timeDropdown.getSelectedItem().toString();
                parent.getItemAtPosition(pos);
                Log.i("Selected item : ", time);
                timeTxt = time;
                switch (pos) {
                    case 0: startTime = 800;
                            endTime = 1100;
                        break;
                    case 1: startTime = 1100;
                            endTime = 1400;
                        break;
                    case 2: startTime = 1400;
                            endTime = 1700;
                        break;
                    case 3: startTime = 1700;
                            endTime = 2000;
                        break;
                    case 4: startTime = 2000;
                            endTime = 2300;
                        break;
                    default: startTime = 0000; // might have to be 0
                             endTime = 2400;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Empty because we must have something selected
            }

        });
        String[] timeItems = new String[]{"8:00am - 11:00am", "11:00am - 2:00pm", "2:00pm - 5:00pm", "5:00pm - 8:00pm", "8:00pm - 11:00pm"};

        Button searchbtn = (Button) findViewById(R.id.searchbtn);

        // Create the arrays for the drop down menu for prices
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, priceItems);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceDropdown.setAdapter(priceAdapter);

        // Create the arrays for the drop down menu for times
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, timeItems);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(timeAdapter);

        // Create the arrays for the drop down menu for days
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, dayItems);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dayDropdown.setAdapter(dayAdapter);

        // Once the search button is selected, go to the BarberSearchResults page
        searchbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You selected price : " + priceTxt + "\n @ " + timeTxt, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        FindBarber.this,
                        BarberSearchResults.class);

                // Pass in the price and time to the BarberSearchResults page
                Bundle b = new Bundle();
                b.putString("day", dayTxt);
                b.putInt("dayPos", dayPos);
                b.putInt("low", low);
                b.putInt("high", high);
                b.putInt("startTime", startTime);
                b.putInt("endTime", endTime);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
