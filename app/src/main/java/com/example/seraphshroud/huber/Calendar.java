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

import com.parse.ParseUser;

import java.util.Arrays;


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

    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;

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

        time0 = (TextView) findViewById(R.id.time0);
        time1 = (TextView) findViewById(R.id.time1);
        time2 = (TextView) findViewById(R.id.time2);
        time3 = (TextView) findViewById(R.id.time3);
        time4 = (TextView) findViewById(R.id.time4);
        time5 = (TextView) findViewById(R.id.time5);
        time6 = (TextView) findViewById(R.id.time6);
        time7 = (TextView) findViewById(R.id.time7);
        time8 = (TextView) findViewById(R.id.time8);
        time9 = (TextView) findViewById(R.id.time9);
        time10 = (TextView) findViewById(R.id.time10);
        time11 = (TextView) findViewById(R.id.time11);
        time12 = (TextView) findViewById(R.id.time12);
        time13 = (TextView) findViewById(R.id.time13);
    }

    public void buttonOnClick(View v)
    {
        ((Button) v).setText("Added!");

        //TODO: Add Code to Add the Data to the Database
        //dayOut.setText("");
        //timeOut1.setText("");
        //timeOut2.setText("");


       /* time0.setText("");
        time1.setText("");
        time2.setText("");
        time3.setText("");
        time4.setText("");
        time5.setText("");
        time6.setText("");
        time7.setText("");
        time8.setText("");
        time9.setText("");
        time10.setText("");
        time11.setText("");
        time12.setText("");
        time13.setText("");
        */

        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.remove("schedule");
        currentUser.add("schedule", time0.getText().toString() + " " + time1.getText().toString() );
        currentUser.add("schedule", time2.getText().toString() + " " + time3.getText().toString() );
        currentUser.add("schedule", time4.getText().toString() + " " + time5.getText().toString() );
        currentUser.add("schedule", time6.getText().toString() + " " + time7.getText().toString() );
        currentUser.add("schedule", time8.getText().toString() + " " + time9.getText().toString() );
        currentUser.add("schedule", time10.getText().toString() + " " + time11.getText().toString());
        currentUser.add("schedule", time12.getText().toString() + " " + time13.getText().toString() );


        //String a[] = new String[] {"Goodbye", "Adele"};
        //currentUser.add("schedule", "Hello");
        currentUser.saveInBackground();
        Intent intent = new Intent(
                Calendar.this,
                BarberWelcome.class);
        startActivity(intent);

    }

    public void showTimePickerDialog(View v) {

        DialogFragment newFrag = new TimePickerFragment(time0);
        FragmentManager frag = getSupportFragmentManager();
        newFrag.show(frag, "timePicker");
    }

    public void showTimePickerDialog2(View v) {
        DialogFragment newFrag2 = new TimePickerFragment(time1);
        FragmentManager frag2 = getSupportFragmentManager();
        newFrag2.show(frag2, "timePicker2");
    }

    public void showTimePickerDialog3(View v) {

        DialogFragment newFrag3 = new TimePickerFragment(time2);
        FragmentManager frag3 = getSupportFragmentManager();
        newFrag3.show(frag3, "timePicker3");
    }

    public void showTimePickerDialog4(View v) {
        DialogFragment newFrag4 = new TimePickerFragment(time3);
        FragmentManager frag4 = getSupportFragmentManager();
        newFrag4.show(frag4, "timePicker4");
    }

    public void showTimePickerDialog5(View v) {

        DialogFragment newFrag5 = new TimePickerFragment(time4);
        FragmentManager frag5 = getSupportFragmentManager();
        newFrag5.show(frag5, "timePicker5");
    }

    public void showTimePickerDialog6(View v) {
        DialogFragment newFrag6 = new TimePickerFragment(time5);
        FragmentManager frag6 = getSupportFragmentManager();
        newFrag6.show(frag6, "timePicker6");
    }

    public void showTimePickerDialog7(View v) {

        DialogFragment newFrag7 = new TimePickerFragment(time6);
        FragmentManager frag7 = getSupportFragmentManager();
        newFrag7.show(frag7, "timePicker7");
    }

    public void showTimePickerDialog8(View v) {
        DialogFragment newFrag8 = new TimePickerFragment(time7);
        FragmentManager frag8 = getSupportFragmentManager();
        newFrag8.show(frag8, "timePicker8");
    }

    public void showTimePickerDialog9(View v) {

        DialogFragment newFrag9 = new TimePickerFragment(time8);
        FragmentManager frag9 = getSupportFragmentManager();
        newFrag9.show(frag9, "timePicker9");
    }

    public void showTimePickerDialog10(View v) {
        DialogFragment newFrag10 = new TimePickerFragment(time9);
        FragmentManager frag10 = getSupportFragmentManager();
        newFrag10.show(frag10, "timePicker10");
    }

    public void showTimePickerDialog11(View v) {

        DialogFragment newFrag11 = new TimePickerFragment(time10);
        FragmentManager frag11 = getSupportFragmentManager();
        newFrag11.show(frag11, "timePicker11");
    }

    public void showTimePickerDialog12(View v) {
        DialogFragment newFrag12 = new TimePickerFragment(time11);
        FragmentManager frag12 = getSupportFragmentManager();
        newFrag12.show(frag12, "timePicker12");
    }

    public void showTimePickerDialog13(View v) {

        DialogFragment newFrag13 = new TimePickerFragment(time12);
        FragmentManager frag13 = getSupportFragmentManager();
        newFrag13.show(frag13, "timePicker13");
    }

    public void showTimePickerDialog14(View v) {
        DialogFragment newFrag14 = new TimePickerFragment(time13);
        FragmentManager frag14 = getSupportFragmentManager();
        newFrag14.show(frag14, "timePicker14");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment(dayOut);
        FragmentManager frag = getSupportFragmentManager();
        newFragment.show(frag, "datePicker");
    }
}
