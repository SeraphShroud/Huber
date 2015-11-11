package com.example.seraphshroud.huber;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by Jason on 11/5/15.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TextView timeView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    @SuppressLint("ValidFragment")
    public TimePickerFragment(TextView time) {
        timeView = time;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String min;

        if(minute < 10) {
            min = "0" + Integer.toString(minute);
        }
        else {
            min = Integer.toString(minute);
        }

        if(hourOfDay >= 12) {
            if(hourOfDay > 12) {
                hourOfDay -= 12;
            }
            timeView.setText(Integer.toString(hourOfDay) + ":" + min + "pm");
        }
        else if(hourOfDay == 0) {
            hourOfDay = 12;
            timeView.setText(Integer.toString(hourOfDay) + ":" + min + "am");
        }
        else {
            timeView.setText(Integer.toString(hourOfDay) + ":" + min + "am");
        }
    }
}
