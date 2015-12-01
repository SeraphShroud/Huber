package com.example.seraphshroud.huber;

/**
 * Created by Alicia on 11/27/2015.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    final ArrayList<String> bName;
    final ArrayList<String> bLoc;
    final ArrayList<Double> bPrice;
    final ArrayList<String> bAvail;

    final DecimalFormat df = new DecimalFormat("#.00");

    public CustomListAdapter(Activity context,  final ArrayList<String> barberName, final ArrayList<String> barberLoc,
                             final ArrayList<Double> barberPrice,  final ArrayList<String> barberAvail) {
        super(context, R.layout.mylist, barberName);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.bName = barberName;
        this.bLoc = barberLoc;
        this.bPrice = barberPrice;
        this.bAvail = barberAvail;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.mylist, null, true);

        TextView txtbName = (TextView) rowView.findViewById(R.id.barberName);
        TextView txtbLoc = (TextView) rowView.findViewById(R.id.barberLoc);
        TextView txtbPrice = (TextView) rowView.findViewById(R.id.barberPrice);
        TextView txtbAvail = (TextView) rowView.findViewById(R.id.barberAvail);


        txtbName.setText(bName.get(position));
        txtbLoc.setText(bLoc.get(position));
        txtbPrice.setText("$" + bPrice.get(position).toString());
        txtbAvail.setText(bAvail.get(position));

        return rowView;

    };
}
