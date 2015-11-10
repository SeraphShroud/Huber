package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Alicia on 11/10/2015.
 */
public class FindBarber extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.find_barber);

        Spinner priceDropdown = (Spinner) findViewById(R.id.price_range);
        String[] priceItems = new String[]{"$0-$10", "$10-$15", "$15-$20", "$20-$25"};

        Spinner timeDropdown = (Spinner) findViewById(R.id.time_range);
        String[] timeItems = new String[]{"8:00am - 11:00am", "11:00am - 2:00pm", "2:00pm - 5:00pm"};

        Button searchbtn = (Button) findViewById(R.id.searchbtn);

        ArrayAdapter<String> priceAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, priceItems);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceDropdown.setAdapter(priceAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(FindBarber.this, android.R.layout.simple_spinner_item, timeItems);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeDropdown.setAdapter(timeAdapter);

        final String priceTxt = priceDropdown.getSelectedItem().toString();
        String timeTxt = timeDropdown.getSelectedItem().toString();

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "You select price @ : " + priceTxt, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(
                        FindBarber.this,
                        BarberSearchResults.class);
                startActivity(intent);
            }
        });

    }
}
