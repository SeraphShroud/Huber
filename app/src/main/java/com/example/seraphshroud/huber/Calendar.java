package com.example.seraphshroud.huber;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Calendar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*dayAdapter = new ArrayAdapter<String>(this, R.layout.activity_calendar, day);
        timeAdapter1 = new ArrayAdapter<String>(this, R.layout.activity_calendar, time1);
        timeAdapter2 = new ArrayAdapter<String>(this, R.layout.activity_calendar, time2);*/

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private EditText dayIn;
    private EditText timeIn1;
    private EditText timeIn2;
    private TextView dayOut;
    private TextView timeOut1;
    private TextView timeOut2;
    /*private ListView dayList;
    private ListView timeList1;
    private ListView timeList2;
    private ArrayList<String> day = new ArrayList<String>();
    private ArrayList<String> time1 = new ArrayList<String>();
    private ArrayList<String> time2 = new ArrayList<String>();
    private ArrayAdapter<String> dayAdapter;
    ArrayAdapter<String> timeAdapter1;
    ArrayAdapter<String> timeAdapter2;*/


    public void buttonOnClick(View v)
    {
        Button button = (Button) v;
        ((Button) v).setText("Added!");

        dayIn = (EditText) findViewById(R.id.editText);
        timeIn1 = (EditText) findViewById(R.id.editText3);
        timeIn2 = (EditText) findViewById(R.id.editText4);

        dayOut = (TextView) findViewById(R.id.textView4);
        timeOut1 = (TextView) findViewById(R.id.textView5);
        timeOut2 = (TextView) findViewById(R.id.textView6);

        dayOut.setText(dayIn.getText());
        timeOut1.setText(timeIn1.getText());
        timeOut2.setText(timeIn2.getText());


        /*day.add(dayIn.toString());
        time1.add(timeIn1.toString());
        time2.add(timeIn2.toString());

        dayList = (ListView) findViewById(R.id.listView);
        dayList.setAdapter(dayAdapter);

        timeList1 = (ListView) findViewById(R.id.listView2);
        timeList1.setAdapter(timeAdapter1);

        timeList2 = (ListView) findViewById(R.id.listView3);
        timeList2.setAdapter(timeAdapter2);*/







    }

}
