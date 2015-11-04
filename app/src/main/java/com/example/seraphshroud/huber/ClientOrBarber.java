package com.example.seraphshroud.huber;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseUser;

/**
 * Created by SeraphShroud on 11/3/2015.
 */
public class ClientOrBarber extends Activity {

    Button clientbutton;
    Button barberbutton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.clientorbarber);

        clientbutton = (Button) findViewById(R.id.clientbutton);
        barberbutton = (Button) findViewById(R.id.barberbutton);

        clientbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(
                        ClientOrBarber.this, ClientInfo.class);
                startActivity(intent);
            }

            // Locate TextView in welcome.xml
            TextView txtuser = (TextView) findViewById(R.id.txtuser);

        });
        barberbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                Intent intent = new Intent(
                        ClientOrBarber.this, BarberInfo.class);
                startActivity(intent);
            }

            // Locate TextView in welcome.xml
            TextView txtuser = (TextView) findViewById(R.id.txtuser);

        });
    }
}