package com.example.sinan.tvstream.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.TimeUtils;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sinan.tvstream.AppMenager;
import com.example.sinan.tvstream.Customs.CustomVolleyRequestQueue;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.R;
import com.example.sinan.tvstream.Utils.IconLoaderAsync;
import com.example.sinan.tvstream.Utils.StringUtils;
import com.example.sinan.tvstream.VideoPlayerActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class channelPage extends Fragment {
    private String TAG = "Channel Fragment";

    AppMenager app = AppMenager.getInstance();
    ImageLoader imgLoader;
    int position;

    public channelPage() {
        // Required empty public constructor
    }

  /*  public channelPage(int positionOfChannel){
        super();
        app = AppMenager.getInstance();
        this.position = positionOfChannel;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channel_page, container, false);

            Bundle args = getArguments();
            final Channel channel = (Channel) args.getSerializable("channel");
            if(channel!=null) {

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(app, VideoPlayerActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtra("channel", channel);
                        startActivity(i);

                    }
                });

                Log.e(TAG, "Channel name:"+channel.getName());
                if(channel.getCurrentProgramme()!=null) {
                    Log.e(TAG, channel.getCurrentProgramme().getTitle());
                    ((TextView) view.findViewById(R.id.txtCurrentShowTitle)).setText(channel.getCurrentProgramme().getTitle());

                    NetworkImageView currentProgImage = (NetworkImageView) view.findViewById(R.id.imgSliderCurrentShow);
                    String currentProgrammeIconUrl = channel.getCurrentProgramme().getIcon().getAttributes().getSrc();
                    if(imgLoader==null){
                        imgLoader = CustomVolleyRequestQueue.getInstance(getContext()).getImageLoader();
                    }
                    currentProgImage.setImageUrl(currentProgrammeIconUrl, imgLoader);
                    //new IconLoaderAsync(currentProgImage).execute(currentProgrammeIconUrl);

                    NetworkImageView channelImageView = (NetworkImageView) view.findViewById(R.id.imgCurrentShowChanell);
                    String channelIconUrl = channel.getImg();
                    channelImageView.setImageUrl(channelIconUrl, imgLoader);
                    new IconLoaderAsync(channelImageView).execute(channelIconUrl);

                    ProgressBar pbCurrentProgramme = (ProgressBar) view.findViewById(R.id.pbCurrentProgramme);


                   /* Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
                    Date currentLocalTime = cal.getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm");
// you can get seconds by adding  "...:ss" to it
                    date.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
                    String localTime = date.format(currentLocalTime);
                    Log.e(TAG, "Local Time:"+localTime);*/

                    String programmeStart = StringUtils.timeSubstring(channel.getCurrentProgramme().getAttributes().getStart());
                    String programmeStop = StringUtils.timeSubstring(channel.getCurrentProgramme().getAttributes().getStop());
                    Log.e(TAG, "Start:"+programmeStart+" Stop:"+programmeStop);

                   /* SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    try {
                        Date start = sdf.parse(programmeStart);
                        Date end = sdf.parse(programmeStop);
                        Date b = sdf.parse(localTime);
                        double razlika = b.getTime() - start.getTime();
                        Log.e(TAG, "razlika izmedju "+b.toString()+" i "+start.toString()+" je "+razlika);
                        double trajanje = end.getTime() - start.getTime();
                        Log.e(TAG, "Trajanje programa:"+trajanje);
                        double procenat = razlika/trajanje*100;
                        Log.e(TAG, "Procenat je:"+procenat);
                        pbCurrentProgramme.setProgress((int) procenat);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }*/

                    TextView txtStart = ((TextView) view.findViewById(R.id.txtProgrammeStart));
                    txtStart.setText(programmeStart);

                    TextView txtEnd = ((TextView) view.findViewById(R.id.txtProgrammeEnd));
                    txtEnd.setText(programmeStop);

                    pbCurrentProgramme.setProgress(channel.getCurrentProgramme().getTimeElapsed());
                    /*Log.e(TAG, "URL String:"+strUrl);
                    URL iconUrl = new URL(strUrl);
                    Log.e(TAG, "URL Url"+iconUrl.toString());
                    Bitmap bmp = BitmapFactory.decodeStream((InputStream) iconUrl.getContent());
                    Log.e(TAG, "Bitmap:"+bmp.getByteCount());
                    ((ImageView)view.findViewById(R.id.imgSliderCurrentShow)).setImageBitmap(bmp);*/
                }else{
                    Log.e(TAG, "Current programme is null");
                }
            }

            // Inflate the layout for this fragment
        return view;

    }

}
