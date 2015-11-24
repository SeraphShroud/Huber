package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.ParseException;

/**
 * Created by Alicia on 11/19/2015.
 */
public class BarberProfile extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.barber_profile);

        // Retrieve all the information from barber's search results to display on profile page
        String nameTxt = getIntent().getExtras().getString("name");
        String locationTxt = getIntent().getExtras().getString("location");
        String priceTxt = getIntent().getExtras().getString("price");
        String specTxt = getIntent().getExtras().getString("specialty");

        final Button messageBtn = (Button) findViewById(R.id.messageBtn);
        final EditText messageTxt = (EditText) findViewById(R.id.message);


        TextView barberNameTxt = (TextView) findViewById(R.id.barber_name);
        TextView barberPriceTxt = (TextView) findViewById(R.id.barber_price);
        final TextView barberLocationTxt = (TextView) findViewById(R.id.barber_location);
        TextView barberSpecTxt = (TextView) findViewById(R.id.barber_specialty);

        barberNameTxt.setText(nameTxt);
        barberLocationTxt.setText(locationTxt);
        barberPriceTxt.setText(priceTxt);
        barberSpecTxt.setText(specTxt);



        messageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                LayoutInflater layin = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layin.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Button dismissBtn = (Button) popupView.findViewById(R.id.dismiss);
                Button sendBtn = (Button) popupView.findViewById(R.id.send);
                popupWindow.setFocusable(true);

                sendBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Send the message to specific barber
                        //ParseUser bUser = ParseUser.getCurrentUser();
                        //barberMessage = messageTxt.getText();
                        //bUser.put("message", barberMessage);
                        //bUser.saveInBackground();
                        popupWindow.dismiss();
                    }
                });

                dismissBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.showAsDropDown(barberLocationTxt, Gravity.LEFT, Gravity.TOP);
            }
        });
    }
}
