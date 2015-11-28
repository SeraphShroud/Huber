package com.example.seraphshroud.huber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alicia on 11/28/2015.
 */
public class MessageListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    final ArrayList<String> cName;
    final ArrayList<String> cDay;
    final ArrayList<String> cTime;
    final ArrayList<String> cMessage;

    public MessageListAdapter(Activity context,  final ArrayList<String> clientName, final ArrayList<String> clientDay,
                              final ArrayList<String> clientTime, final ArrayList<String> clientMessage ) {

        super(context, R.layout.mylist, clientName);

        this.context = context;
        this.cName = clientName;
        this.cDay = clientDay;
        this.cTime = clientTime;
        this.cMessage = clientMessage;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.barber_welcome_list, null, true);

        TextView txtcName = (TextView) rowView.findViewById(R.id.clientName);
        TextView txtcDate = (TextView) rowView.findViewById(R.id.clientDate);
        TextView txtcMessage = (TextView) rowView.findViewById(R.id.message);


        txtcName.setText(cName.get(position));
        txtcDate.setText(cDay.get(position) + " " + cTime.get(position) );
        txtcMessage.setText(cMessage.get(position));

        return rowView;

    };
}
