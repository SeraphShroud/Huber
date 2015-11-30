package com.example.seraphshroud.huber;

/**
 * Created by Alicia on 11/28/2015.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ApptListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    final ArrayList<String> bName;
    final ArrayList<String> bPhone;
    final ArrayList<String> bDay;
    final ArrayList<String> bTime;
    final ArrayList<String> bConfirm;
    final ArrayList<Date> bCreatedAt;

    public ApptListAdapter(Activity context,  final ArrayList<String> barberName, final ArrayList<String> barberPhone,
                           final ArrayList<String> barberDay,  final ArrayList<String> barberTime,
                           final ArrayList<String> barberConfirm, final ArrayList<Date> barberCreatedAt ) {

        super(context, R.layout.mylist, barberName);

        this.context = context;
        this.bName = barberName;
        this.bPhone = barberPhone;
        this.bDay = barberDay;
        this.bTime = barberTime;
        this.bConfirm = barberConfirm;
        this.bCreatedAt = barberCreatedAt;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.client_welcome_list, null, true);

        TextView txtbName = (TextView) rowView.findViewById(R.id.barberName);
        TextView txtbDate = (TextView) rowView.findViewById(R.id.barberDate);
        TextView txtbConfirmed = (TextView) rowView.findViewById(R.id.confirmed);
        TextView txtbPhone = (TextView) rowView.findViewById(R.id.barberPhone);
        TextView txtbCreatedAt = (TextView) rowView.findViewById(R.id.date);


        txtbName.setText(bName.get(position));
        txtbDate.setText(bDay.get(position) + " " + bTime.get(position) );
        txtbConfirmed.setText(bConfirm.get(position));

        Date date = bCreatedAt.get(position);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String reportDate = df.format(date);
        txtbCreatedAt.setText(reportDate);

        if(bConfirm.get(position).compareTo("Accepted") == 0)
            txtbPhone.setText(bPhone.get(position));
        else
            txtbPhone.setText("Please schedule another appointment time.");

        return rowView;

    };
}


