package com.example.sinan.tvstream.Customs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sinan.tvstream.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Sinan on 23.6.2016.
 */
public class MyDateArrayAdapter extends ArrayAdapter<Date> {
    private String TAG = "MyDateArrayAdapter";
    private List<Date> listOfProgrammDates;
    private Context ctxt;
    public MyDateArrayAdapter(Context context, int resource, List<Date> listOfProgrammDates) {
        super(context, resource);
        this.listOfProgrammDates = listOfProgrammDates;
        this.ctxt = context;
        Log.e(TAG, "in constructor listOfProgrammDates.size()="+listOfProgrammDates.size()+" and this.listOfProgrammDates.size()="+this.listOfProgrammDates.size());
    }

    @Override
    public int getCount() {
        if(listOfProgrammDates!=null){
            return listOfProgrammDates.size();
        }else{
            return 0;
        }
    }

    public void refreshData(List<Date> dates){
        Log.e(TAG, "in refreshData this.listOfProgrammDates.size()="+this.listOfProgrammDates.size()+" and parameter dates.size()="+dates.size());

        //this.listOfProgrammDates.clear();
        //this.listOfProgrammDates.addAll(dates);
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.date_spinner_row, parent, false);
        TextView txtDate = (TextView) row.findViewById(R.id.txtDate);


        Date today = new Date();
        String todayStr = today.toString().substring(0, 10);

        Date currentDate=listOfProgrammDates.get(position);
        String currentDateStr = currentDate.toString().substring(0, 10);
        Log.e(TAG, "in getCustomView this.listOfProgrammDates.size()="+this.listOfProgrammDates.size());
        if(currentDateStr.equals(todayStr)) {
            txtDate.setText("Today");
        }else{
            txtDate.setText(currentDateStr);
        }
        return row;
    }
}
