package com.example.seraphshroud.huber;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Calendar extends AppCompatActivity {

    private TextView dayOut;

    public TextView time0;
    public TextView time1;
    public TextView time2;
    public TextView time3;
    public TextView time4;
    public TextView time5;
    public TextView time6;
    public TextView time7;
    public TextView time8;
    public TextView time9;
    public TextView time10;
    public TextView time11;
    public TextView time12;
    public TextView time13;

    private TextView[] tvarray = new TextView[14];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //dayOut = (TextView) findViewById(R.id.textView4);
        //timeOut1 = (TextView) findViewById(R.id.textView5);
        //timeOut2 = (TextView) findViewById(R.id.textView6);

        //monday = (TextView) findViewById(R.id.monday);
        //tuesday = (TextView) findViewById(R.id.tuesday);
        //wednesday = (TextView) findViewById(R.id.wednesday);
        //thursday = (TextView) findViewById(R.id.thursday);
        //friday = (TextView) findViewById(R.id.friday);
        //saturday = (TextView) findViewById(R.id.saturday);
        //sunday = (TextView) findViewById(R.id.sunday);

        tvarray[0] = (TextView) findViewById(R.id.time0);
        tvarray[1] = (TextView) findViewById(R.id.time1);
        tvarray[2] = (TextView) findViewById(R.id.time2);
        tvarray[3] = (TextView) findViewById(R.id.time3);
        tvarray[4] = (TextView) findViewById(R.id.time4);
        tvarray[5] = (TextView) findViewById(R.id.time5);
        tvarray[6] = (TextView) findViewById(R.id.time6);
        tvarray[7] = (TextView) findViewById(R.id.time7);
        tvarray[8] = (TextView) findViewById(R.id.time8);
        tvarray[9] = (TextView) findViewById(R.id.time9);
        tvarray[10] = (TextView) findViewById(R.id.time10);
        tvarray[11] = (TextView) findViewById(R.id.time11);
        tvarray[12] = (TextView) findViewById(R.id.time12);
        tvarray[13] = (TextView) findViewById(R.id.time13);

        ParseUser user = ParseUser.getCurrentUser();
        List<String> temp;

        temp = user.getList("schedule");

        if(temp != null) {
            String[] schedule = new String[7];
            temp.toArray(schedule);
            /*
            time0.setText(schedule[0].substring(0, schedule[0].indexOf(" ")));
            time1.setText(schedule[0].substring(schedule[0].indexOf(" ") + 1));
            time2.setText(schedule[1].substring(0, schedule[1].indexOf(" ")));
            time3.setText(schedule[1].substring(schedule[1].indexOf(" ") + 1));
            time4.setText(schedule[2].substring(0, schedule[2].indexOf(" ")));
            time5.setText(schedule[2].substring(schedule[2].indexOf(" ") + 1));
            time6.setText(schedule[3].substring(0, schedule[3].indexOf(" ")));
            time7.setText(schedule[3].substring(schedule[3].indexOf(" ") + 1));
            time8.setText(schedule[4].substring(0, schedule[4].indexOf(" ")));
            time9.setText(schedule[4].substring(schedule[4].indexOf(" ") + 1));
            time10.setText(schedule[5].substring(0, schedule[5].indexOf(" ")));
            time11.setText(schedule[5].substring(schedule[5].indexOf(" ") + 1));
            time12.setText(schedule[6].substring(0, schedule[6].indexOf(" ")));
            time13.setText(schedule[6].substring(schedule[6].indexOf(" ") + 1));
            */
            for (int i = 0; i < 14; i+=2) {
                tvarray[i].setText(schedule[i/2].substring(0, schedule[i/2].indexOf(" ")));
                tvarray[i+1].setText(schedule[i/2].substring(schedule[i/2].indexOf(" ") + 1));
            }
        }
    }

    public void buttonOnClick(View v)
    {
        ((Button) v).setText("Added!");

        //TODO: Add Code to Add the Data to the Database

        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.remove("schedule");
        for (int i = 0; i < 14; i+=2) {
            if( tvarray[i].getText().toString() == "time" || tvarray[i+1].getText().toString() == "time")
                currentUser.add("schedule", "0 0");
            else
                currentUser.add("schedule", tvarray[i].getText().toString() + " " + tvarray[i+1].getText().toString() );
        }
        /*
        currentUser.add("schedule", time0.getText().toString() + " " + time1.getText().toString() );
        currentUser.add("schedule", time2.getText().toString() + " " + time3.getText().toString() );
        currentUser.add("schedule", time4.getText().toString() + " " + time5.getText().toString() );
        currentUser.add("schedule", time6.getText().toString() + " " + time7.getText().toString() );
        currentUser.add("schedule", time8.getText().toString() + " " + time9.getText().toString() );
        currentUser.add("schedule", time10.getText().toString() + " " + time11.getText().toString() );
        currentUser.add("schedule", time12.getText().toString() + " " + time13.getText().toString() );
        */

        currentUser.saveInBackground();
        Intent intent = new Intent(
                Calendar.this,
                BarberWelcome.class);
        startActivity(intent);

    }

    public void showTimePickerDialog(View v) {

        DialogFragment newFrag = new TimePickerFragment(tvarray[0]);
        FragmentManager frag = getSupportFragmentManager();
        newFrag.show(frag, "timePicker");
    }

    public void showTimePickerDialog2(View v) {
        DialogFragment newFrag2 = new TimePickerFragment(tvarray[1]);
        FragmentManager frag2 = getSupportFragmentManager();
        newFrag2.show(frag2, "timePicker2");
    }

    public void showTimePickerDialog3(View v) {

        DialogFragment newFrag3 = new TimePickerFragment(tvarray[2]);
        FragmentManager frag3 = getSupportFragmentManager();
        newFrag3.show(frag3, "timePicker3");
    }

    public void showTimePickerDialog4(View v) {
        DialogFragment newFrag4 = new TimePickerFragment(tvarray[3]);
        FragmentManager frag4 = getSupportFragmentManager();
        newFrag4.show(frag4, "timePicker4");
    }

    public void showTimePickerDialog5(View v) {

        DialogFragment newFrag5 = new TimePickerFragment(tvarray[4]);
        FragmentManager frag5 = getSupportFragmentManager();
        newFrag5.show(frag5, "timePicker5");
    }

    public void showTimePickerDialog6(View v) {
        DialogFragment newFrag6 = new TimePickerFragment(tvarray[5]);
        FragmentManager frag6 = getSupportFragmentManager();
        newFrag6.show(frag6, "timePicker6");
    }

    public void showTimePickerDialog7(View v) {

        DialogFragment newFrag7 = new TimePickerFragment(tvarray[6]);
        FragmentManager frag7 = getSupportFragmentManager();
        newFrag7.show(frag7, "timePicker7");
    }

    public void showTimePickerDialog8(View v) {
        DialogFragment newFrag8 = new TimePickerFragment(tvarray[7]);
        FragmentManager frag8 = getSupportFragmentManager();
        newFrag8.show(frag8, "timePicker8");
    }

    public void showTimePickerDialog9(View v) {

        DialogFragment newFrag9 = new TimePickerFragment(tvarray[8]);
        FragmentManager frag9 = getSupportFragmentManager();
        newFrag9.show(frag9, "timePicker9");
    }

    public void showTimePickerDialog10(View v) {
        DialogFragment newFrag10 = new TimePickerFragment(tvarray[9]);
        FragmentManager frag10 = getSupportFragmentManager();
        newFrag10.show(frag10, "timePicker10");
    }

    public void showTimePickerDialog11(View v) {

        DialogFragment newFrag11 = new TimePickerFragment(tvarray[10]);
        FragmentManager frag11 = getSupportFragmentManager();
        newFrag11.show(frag11, "timePicker11");
    }

    public void showTimePickerDialog12(View v) {
        DialogFragment newFrag12 = new TimePickerFragment(tvarray[11]);
        FragmentManager frag12 = getSupportFragmentManager();
        newFrag12.show(frag12, "timePicker12");
    }

    public void showTimePickerDialog13(View v) {

        DialogFragment newFrag13 = new TimePickerFragment(tvarray[12]);
        FragmentManager frag13 = getSupportFragmentManager();
        newFrag13.show(frag13, "timePicker13");
    }

    public void showTimePickerDialog14(View v) {
        DialogFragment newFrag14 = new TimePickerFragment(tvarray[13]);
        FragmentManager frag14 = getSupportFragmentManager();
        newFrag14.show(frag14, "timePicker14");
    }
}
