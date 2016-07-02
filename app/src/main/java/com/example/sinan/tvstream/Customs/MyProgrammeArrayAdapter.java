package com.example.sinan.tvstream.Customs;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinan on 23.6.2016.
 */
public class MyProgrammeArrayAdapter extends ArrayAdapter<EPG.Programme> {
    String TAG = "MyProgrammeArrayAdapter";
    public List<EPG.Programme> listOfProgrammes;
    Context ctxt;

    public MyProgrammeArrayAdapter(Activity context, int resource, List<EPG.Programme> listOfProgrammes) {
        super(context, resource);
        this.listOfProgrammes = listOfProgrammes;
        this.ctxt = context;
    }

    public void refreshData(List<EPG.Programme> programmes){

        this.listOfProgrammes = null;
        this.listOfProgrammes = new ArrayList<>();
        this.listOfProgrammes = programmes;

        this.notifyDataSetChanged();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View rowView = inflater.inflate(R.layout.channel_programmes_list_item, parent, false);
        Log.e(TAG, "position="+position);
        EPG.Programme programme;
        if(listOfProgrammes.get(position)==null){
            Log.e(TAG, "getProgrammes is null");
            //programme = listOfChannels.get(0).getCurrentProgramme();
        }else {
            programme = listOfProgrammes.get(position);
            NetworkImageView imgEPGProgramme = (NetworkImageView) rowView.findViewById(R.id.imgEPGProgramme);
            ImageLoader imageLoader = CustomVolleyRequestQueue.getInstance(ctxt).getImageLoader();
            imgEPGProgramme.setImageUrl(programme.getIcon().getAttributes().getSrc(), imageLoader);
            //new IconLoaderAsync(imgEPGProgramme).execute(listOfChannels.get(0).getProgrammes().get(0).getIcon().getAttributes().getSrc());

            TextView txtProgrammeTitle = (TextView) rowView.findViewById(R.id.txtProgrammeTitle);
            txtProgrammeTitle.setText(programme.getTitle());

            TextView txtProgrammeStart = (TextView) rowView.findViewById(R.id.txtProgrammeStart);
            txtProgrammeStart.setText(programme.getAttributes().getStartSubstring());

        }
        return rowView;
    }

    @Override
    public int getCount() {
        if(listOfProgrammes!=null) {
            return listOfProgrammes.size();
        }else{
            return 0;
        }
    }
}

