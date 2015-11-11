package com.example.seraphshroud.huber;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Calendar extends AppCompatActivity {

    private TextView dayOut;
    public TextView timeOut1;
    public TextView timeOut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dayOut = (TextView) findViewById(R.id.textView4);
        timeOut1 = (TextView) findViewById(R.id.textView5);
        timeOut2 = (TextView) findViewById(R.id.textView6);

    }

    public void buttonOnClick(View v)
    {
        ((Button) v).setText("Added!");

        //TODO: Add Code to Add the Data to the Database
        dayOut.setText("");
        timeOut1.setText("");
        timeOut2.setText("");
    }

    public void showTimePickerDialog(View v) {

        DialogFragment newFragment = new TimePickerFragment(timeOut1);
        FragmentManager frag = getSupportFragmentManager();
        newFragment.show(frag, "timePicker");
    }

    public void showTimePickerDialog2(View v) {
        DialogFragment newFrag = new TimePickerFragment(timeOut2);
        FragmentManager frag2 = getSupportFragmentManager();
        newFrag.show(frag2, "timePicker2");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(dayOut);
        FragmentManager frag = getSupportFragmentManager();
        newFragment.show(frag, "datePicker");
    }
}
