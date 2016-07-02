package com.example.sinan.tvstream.Customs;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.R;

import java.util.List;

/**
 * Created by Sinan on 22.6.2016.
 */
public class MyChannelsArrayAdapter extends ArrayAdapter<Channel> {
    List<Channel> listOfChannels;
    Context ctxt;
    public MyChannelsArrayAdapter(Context context, int resource, List<Channel> listOfChannels) {
        super(context, resource);
        this.listOfChannels = listOfChannels;
        this.ctxt = context;
        Log.e("MyChnnelAdapter", "constructor listoOfChannels.size()="+this.listOfChannels.size());
    }

    @Override
    public int getCount() {
        if(listOfChannels!=null){
            return listOfChannels.size();
        }else{
            return 0;
        }
    }

    public void refreshData(List<Channel> channels){
        this.listOfChannels = null;
        this.listOfChannels = channels;
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
        LayoutInflater inflater = (LayoutInflater) getContext().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View row = inflater.inflate(R.layout.channel_spinner_row, parent, false);

        Channel channel = listOfChannels.get(position);

        NetworkImageView imgBtnChannelSpinner = (NetworkImageView) row.findViewById(R.id.imgBtnChannelSpinner);
        ImageLoader loader = CustomVolleyRequestQueue.getInstance(ctxt).getImageLoader();
        imgBtnChannelSpinner.setImageUrl(channel.getImg(), loader);

        Log.e("MyChnnelAdapter", "getCustomView "+row.toString());

        return row;
    }
}
