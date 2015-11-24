package com.example.seraphshroud.huber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Alicia on 11/19/2015.
 */
public class BarberProfile extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.barber_profile);

        final DecimalFormat df = new DecimalFormat("#.00");

        // Retrieve all the information from barber's search results to display on profile page
        String nameTxt = getIntent().getExtras().getString("name");
        String locationTxt = getIntent().getExtras().getString("location");
        double price = getIntent().getExtras().getDouble("price");
        String priceTxt = "$" + df.format(price);
        String specTxt = getIntent().getExtras().getString("specialty");

        TextView barberNameTxt = (TextView) findViewById(R.id.barber_name);
        TextView barberPriceTxt = (TextView) findViewById(R.id.barber_price);
        TextView barberLocationTxt = (TextView) findViewById(R.id.barber_location);
        TextView barberSpecTxt = (TextView) findViewById(R.id.barber_specialty);

        barberNameTxt.setText(nameTxt);
        barberLocationTxt.setText(locationTxt);
        barberPriceTxt.setText(priceTxt);
        barberSpecTxt.setText(specTxt);
    }
}
