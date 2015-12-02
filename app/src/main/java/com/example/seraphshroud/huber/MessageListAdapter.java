package com.example.seraphshroud.huber;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Alicia on 11/28/2015.
 */
public class MessageListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    final ArrayList<String> cName;
    final ArrayList<String> cDay;
    final ArrayList<String> cTime;
    final ArrayList<String> cMessage;
    final ArrayList<Date> cCreatedAt;

    public MessageListAdapter(Activity context,  final ArrayList<String> clientName, final ArrayList<String> clientDay,
                              final ArrayList<String> clientTime, final ArrayList<String> clientMessage,
                              final ArrayList<Date> clientCreatedAt ) {

        super(context, R.layout.mylist, clientName);

        this.context = context;
        this.cName = clientName;
        this.cDay = clientDay;
        this.cTime = clientTime;
        this.cMessage = clientMessage;
        this.cCreatedAt = clientCreatedAt;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.barber_welcome_list, null, true);

        TextView txtcName = (TextView) rowView.findViewById(R.id.clientName);
        TextView txtcDate = (TextView) rowView.findViewById(R.id.clientDate);
        TextView txtcMessage = (TextView) rowView.findViewById(R.id.message);
        TextView txtcCreatedAt = (TextView) rowView.findViewById(R.id.bookDate);


        txtcName.setText(cName.get(position));
        txtcDate.setText(cDay.get(position) + " " + cTime.get(position) );
        txtcMessage.setText(cMessage.get(position));

        Date date = cCreatedAt.get(position);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        String reportDate = df.format(date);
        txtcCreatedAt.setText(reportDate);

        return rowView;

    };
}
