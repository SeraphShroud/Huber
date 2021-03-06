package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;
import java.text.DecimalFormat;
import java.util.List;


/**
 * Created by Alicia on 11/19/2015.
 */
public class BarberProfile extends Activity {

    // Helper/Setter method to get the params from another activity and set it's TextView
    final public void setParams(String param, int id){
        String data = getIntent().getExtras().getString(param);
        TextView dataText = (TextView) findViewById(id);
        dataText.setText(data);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.barber_profile);

        // Retrieve all the information from barber's search results to display on profile page
        final String idTxt = getIntent().getExtras().getString("id");
        setParams("name", R.id.barber_name);
        setParams("location", R.id.barber_location);
        setParams("specialty", R.id.barber_specialty);
        setParams("schedule", R.id.barber_schedule);
        final String scheduleTxt = getIntent().getExtras().getString("schedule");
        final String dayTxt = getIntent().getExtras().getString("day");

        //final TextView s

        // Retrieve barber prices and reformat it
        final DecimalFormat df = new DecimalFormat("#.00");
        double price = getIntent().getExtras().getDouble("price");
        if ( price == 0 ) {
            final TextView barberPriceTxt = (TextView) findViewById(R.id.barber_price);
            barberPriceTxt.setText("FREE");
        }
        else {
            String priceTxt = "$" + df.format(price);
            final TextView barberPriceTxt = (TextView) findViewById(R.id.barber_price);
            barberPriceTxt.setText(priceTxt);
        }

        // When message btn is clicked, popup window should show
        final Button messageBtn = (Button) findViewById(R.id.messageBtn);

        messageBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                LayoutInflater layin = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                final View popupView = layin.inflate(R.layout.popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                Button dismissBtn = (Button) popupView.findViewById(R.id.dismiss);
                Button sendBtn = (Button) popupView.findViewById(R.id.send);
                popupWindow.setFocusable(true);
                final EditText messageTxt = (EditText) popupView.findViewById(R.id.message);

                sendBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Send the message to specific barber
                        ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
                        final ParseUser currentUser = ParseUser.getCurrentUser();
                        query.findInBackground(new FindCallback<ParseObject>() {
                            @Override
                            public void done(List<ParseObject> messageList, com.parse.ParseException e) {
                                if (e == null) {
                                    if (messageList.size() == 0) {
                                        ParseObject parseMessage = new ParseObject("ParseMessage");
                                        final String mess = messageTxt.getText().toString();
                                        parseMessage.put("sender", currentUser);
                                        parseMessage.put("clientName", currentUser.get("name"));
                                        parseMessage.put("receiver",idTxt);
                                        parseMessage.put("message", mess);
                                        parseMessage.put("aptDay", dayTxt);
                                        parseMessage.put("aptTime", scheduleTxt);
                                        parseMessage.saveInBackground();
                                    }
                                }
                            }
                        });
                        Toast.makeText(getApplicationContext(),
                                "Message successfully sent!", Toast.LENGTH_SHORT)
                                .show();
                        popupWindow.dismiss();
                    }
                });

                dismissBtn.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
            }
        });
    }
}
