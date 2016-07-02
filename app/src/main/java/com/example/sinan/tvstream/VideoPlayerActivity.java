package com.example.sinan.tvstream;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import android.net.Uri;

import com.devbrackets.android.exomedia.EMVideoView;
import com.example.sinan.tvstream.Customs.MyChannelsArrayAdapter;
import com.example.sinan.tvstream.Customs.MyProgrammeArrayAdapter;
import com.example.sinan.tvstream.Customs.VideoControllerView;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.Utils.PrefsUtil;
import com.example.sinan.tvstream.Utils.StringUtils;
import com.fasterxml.jackson.databind.util.ClassUtil;


import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoPlayerActivity extends Activity implements View.OnClickListener, SurfaceHolder.Callback, MediaPlayer.OnPreparedListener,MediaPlayer.OnInfoListener, MediaPlayer.OnErrorListener {
    private String TAG="VideoPlayerActivity";
    private AppMenager app;
    private Channel channelPlayed;
    private List<EPG.Programme> channelProgrammes;

    SurfaceView videoSurface;
    MediaPlayer player;
    RelativeLayout gridActionBar;
    ProgressBar progress;
    ImageButton btnPlayerMenu;
    LinearLayout llPlayerMenu;
    LinearLayout llLogoutMenuItem;
    LinearLayout llHomeMenuItem;
    LinearLayout llEpgMenuItem;
    LinearLayout llChannelInfo;
    ImageButton imgBtnChannels;
    ImageButton imgBtnEpg;
    ListPopupWindow popupChannels;
    ListPopupWindow popupEpg;
    boolean isMenuAnimated = false;
    boolean isChannelInfoAnimated=false;
    MyProgrammeArrayAdapter programmesAdapter=null;
    VideoView videoView;

    void setChannelPlayed (Channel channel){
        this.channelPlayed = channel;
        this.channelProgrammes = this.channelPlayed.getProgrammesByDate(new Date());
        if(this.programmesAdapter!=null) {
            this.programmesAdapter.refreshData(channelProgrammes);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        app = AppMenager.getInstance();
        Channel channelFromExtras = (Channel) getIntent().getExtras().getSerializable("channel");
        setChannelPlayed(channelFromExtras);

        gridActionBar = (RelativeLayout) findViewById(R.id.gridActioBar);
        gridActionBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "gridLayout onClick");
            }
        });


        llPlayerMenu = (LinearLayout) findViewById(R.id.llPlayerMenu);
        llLogoutMenuItem = (LinearLayout) llPlayerMenu.findViewById(R.id.llLogoutMenuItem);
        llLogoutMenuItem.setOnClickListener(this);
        llHomeMenuItem = (LinearLayout) llPlayerMenu.findViewById(R.id.llHomeMenuItem);
        llHomeMenuItem.setOnClickListener(this);
        llEpgMenuItem = (LinearLayout) llPlayerMenu.findViewById(R.id.llEpgMenuItem);
        llEpgMenuItem.setOnClickListener(this);
        llChannelInfo = (LinearLayout) findViewById(R.id.llChannelInfo);
        imgBtnChannels = (ImageButton) llChannelInfo.findViewById(R.id.imgBtnChannels);
        imgBtnEpg = (ImageButton) llChannelInfo.findViewById(R.id.imgBtnEpg);
        initChannelInfo();

        imgBtnChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "imgBtnChannels onClick");
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                MyChannelsArrayAdapter channelsAdapter = new MyChannelsArrayAdapter(VideoPlayerActivity.this, R.layout.channel_spinner_row, app.getListOfChannels());
                popupChannels = new ListPopupWindow(VideoPlayerActivity.this);
                popupChannels.setAdapter(channelsAdapter);
                popupChannels.setWidth(300);
                popupChannels.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupChannels.setAnchorView(gridActionBar);
                popupChannels.show();
                popupChannels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        progress.setVisibility(View.VISIBLE);
                        Channel selectedChannel = app.getListOfChannels().get(position);
                        setChannelPlayed(selectedChannel);
                        player.reset();
                        try {
                            player.setDataSource(StringUtils.subrstringHttpUrl(channelPlayed.getUrl()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.prepareAsync();
                    }
                });
            }
        });
        imgBtnEpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "imgBtnEpg onClick");
                View detailsView = getLayoutInflater().inflate(R.layout.programme_details, null);
                popupEpg = new ListPopupWindow(VideoPlayerActivity.this);

                if(programmesAdapter==null) {
                    programmesAdapter = new MyProgrammeArrayAdapter(VideoPlayerActivity.this, R.layout.channel_programmes_list_item, channelProgrammes);
                }
                popupEpg.setAdapter(programmesAdapter);
                popupEpg.setWidth(600);
                popupEpg.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                popupEpg.setAnchorView(gridActionBar);
                popupEpg.show();

                //popup.showAsDropDown(imgBtnEpg);

            }
        });

        btnPlayerMenu = (ImageButton) findViewById(R.id.btnPlayerMenu);
        btnPlayerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "PlayerMenu onClick");
                if(isMenuAnimated){
                    Log.e(TAG, "PlayerMenu onClick clearAnimation");
                    translateView(llPlayerMenu, llPlayerMenu.getWidth());
                }else {
                    Log.e(TAG, "PlayerMenu onClick animate");
                    translateView(llPlayerMenu, -llPlayerMenu.getWidth());
                }

                isMenuAnimated = !isMenuAnimated;

            }
        });

        /*videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(this);

        videoView.setVideoURI(Uri.parse(subrstringHttpUrl(channelPlayed.getUrl())));

        videoView.setOnErrorListener(this);*/

        videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
        SurfaceHolder videoHolder = videoSurface.getHolder();
        videoHolder.addCallback(this);

        player = new MediaPlayer();
        player.setOnErrorListener(this);
        player.setOnInfoListener(this);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setIndeterminate(true);
        progress.setVisibility(View.VISIBLE);


        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(this, Uri.parse(StringUtils.subrstringHttpUrl(channelPlayed.getUrl())));
            player.prepareAsync();
            player.setOnPreparedListener(this);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume current channel:"+channelPlayed.getName());

        if(player.isLooping()){
            Log.e(TAG, "onResume player is looping "+player.toString());
        }
        if(player.isPlaying()){
            Log.e(TAG, "onResume player is playing "+player.toString());
        }else {
            Log.e(TAG, "onResume player not playing "+player.toString());
            player.start();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    float x=0, y=0;

   @Override
    public boolean onTouchEvent(MotionEvent event) {

       int action = MotionEventCompat.getActionMasked(event);
       Log.e(TAG, "action: "+action);

       switch (action){
           case (MotionEvent.ACTION_DOWN) :
               Log.d(TAG,"Action was DOWN ");
               x= event.getX();
               y=event.getY();
               Log.e(TAG, "X:"+x+" Y:"+y);
               return true;
           case (MotionEvent.ACTION_MOVE) :
               Log.d(TAG,"Action was MOVE ");
               return true;
           case (MotionEvent.ACTION_UP) :
               Log.d(TAG,"Action was UP ");
               Log.e(TAG, "x="+x+" y="+y+" <>= "+event.getX()+", "+event.getY());
               float razlikaX = event.getX()-x;
               float razlikaY = y-event.getY();
               if(razlikaX>0 && razlikaX>100 && razlikaY<100){
                   Log.e(TAG, "desno "+(event.getX()-x));
                   progress.setVisibility(View.VISIBLE);
                   moveToPrevChannel();
               }else if(razlikaX<0 && razlikaX < (-100)){
                   Log.e(TAG, "lijevo "+(event.getX()-x));
                   progress.setVisibility(View.VISIBLE);
                   moveToNextChannel();
               }else if(razlikaX == 0){
                   Log.e(TAG, "TAP");
                   showHideControls();
               }else if (y>400 && razlikaY > 50 && razlikaX<100){
                   Log.e(TAG, "from down to up");
                   translateViewAxisY(llChannelInfo, -(llChannelInfo.getHeight()));
                   isChannelInfoAnimated = !isChannelInfoAnimated;
               }

               x=0; y=0;

               return true;

           case (MotionEvent.ACTION_CANCEL) :
               Log.d(TAG,"Action was CANCEL ");
               return true;
           case (MotionEvent.ACTION_OUTSIDE) :
               Log.d(TAG,"Movement occurred outside bounds " +
                       "of current screen element ");
               return true;
           default :
               return super.onTouchEvent(event);
       }


    }

    void moveToNextChannel(){
        setChannelPlayed(getNextChannel());
        initChannelInfo();
        //videoView.setVideoURI(Uri.parse(subrstringHttpUrl(channelPlayed.getUrl())));
        player.reset();
        try {

            player.setDataSource(StringUtils.subrstringHttpUrl(channelPlayed.getUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.prepareAsync();
    }

    void moveToPrevChannel(){
        setChannelPlayed(getPrevChannel());
        initChannelInfo();

        //videoView.setVideoURI(Uri.parse(subrstringHttpUrl(channelPlayed.getUrl())));

        player.reset();
        try {
            player.setDataSource(StringUtils.subrstringHttpUrl(channelPlayed.getUrl()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.prepareAsync();
    }


    Channel getNextChannel(){
        Channel channel=null;
        if(app!=null && app.getListOfChannels()!=null){
            Log.e(TAG, "Channel index= "+app.getListOfChannels().indexOf(channelPlayed));
            //if(app.getListOfChannels().indexOf(channelPlayed)>=0)
            int index = app.getListOfChannels().indexOf(channelPlayed);
            if(index==(-1)){
                index=0;
            }
            channel = app.getListOfChannels().get(index+1);
            return channel;
        }else {
            return channel = channelPlayed;
        }
    }

    Channel getPrevChannel(){
        Channel channel=null;
        if(app!=null && app.getListOfChannels()!=null){
            //if(app.getListOfChannels().indexOf(channelPlayed)>)
            Log.e(TAG, "Channel index= "+app.getListOfChannels().indexOf(channelPlayed));
            int index = app.getListOfChannels().indexOf(channelPlayed);
            if(index>0) {
                channel = app.getListOfChannels().get(app.getListOfChannels().indexOf(channelPlayed) - 1);
            }else{
                channel=channelPlayed;
            }
        }else {
            channel=channelPlayed;
        }

        return  channel;
    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.e(TAG, "onPrepared " );
        showHideControls();
        progress.setVisibility(View.GONE);
        player.start();
        //videoView.start();

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //player.prepareAsync();
        player.setDisplay(holder);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        //videoView.stopPlayback();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //videoView.stopPlayback();
        player.stop();
        player.release();
    }

    void showHideControls(){
        if(isMenuAnimated){
            translateView(llPlayerMenu, llPlayerMenu.getWidth());
            isMenuAnimated = !isMenuAnimated;
        }

        if(gridActionBar.isShown() && isChannelInfoAnimated){
            gridActionBar.setVisibility(View.GONE);
            translateViewAxisY(llChannelInfo, llChannelInfo.getHeight());
            isChannelInfoAnimated = !isChannelInfoAnimated;
        }else if(!gridActionBar.isShown() && isChannelInfoAnimated){
            //gridActionBar.setVisibility(View.VISIBLE);
            translateViewAxisY(llChannelInfo, llChannelInfo.getHeight());
            isChannelInfoAnimated = !isChannelInfoAnimated;
        }else if(gridActionBar.isShown() && !isChannelInfoAnimated){
            gridActionBar.setVisibility(View.GONE);
        }else if(!gridActionBar.isShown() && !isChannelInfoAnimated){
            gridActionBar.setVisibility(View.VISIBLE);
        }



    }

    private void initChannelInfo(){
        if(channelPlayed!=null && llChannelInfo!=null){
            TextView txtProgrammeTitle = (TextView) llChannelInfo.findViewById(R.id.txtProgrammeTitle);
            TextView txtDuration = (TextView) llChannelInfo.findViewById(R.id.txtDuration);
            TextView txtProgrammeStart = (TextView) llChannelInfo.findViewById(R.id.txtProgrammeStart);
            TextView txtProgrammeEnd = (TextView) llChannelInfo.findViewById(R.id.txtProgrammeEnd);
            ProgressBar pbCurrentProgramme = (ProgressBar) llChannelInfo.findViewById(R.id.pbCurrentProgramme);

            txtProgrammeTitle.setText(channelPlayed.getCurrentProgramme().getTitle());
            txtProgrammeStart.setText(channelPlayed.getCurrentProgramme().getAttributes().getStartSubstring());
            txtProgrammeEnd.setText(channelPlayed.getCurrentProgramme().getAttributes().getStopSubstring());
            pbCurrentProgramme.setProgress(channelPlayed.getCurrentProgramme().getTimeElapsed());
            txtDuration.setText(String.valueOf(channelPlayed.getCurrentProgramme().getAttributes().getDuration()));
        }
    }



   @Override
    public void onClick(View v) {

        Log.e(TAG, "onClick "+v.getClass().getSimpleName()+" "+v.getParent());

       switch (v.getId()){
           case R.id.llLogoutMenuItem: Log.e(TAG, "Logout onClick");
                                       PrefsUtil.removeFromPrefs(PrefsUtil.PREFS_LOGIN_USER_DATA_KEY);
                                       Intent iLogout = new Intent(VideoPlayerActivity.this, LoginActivity.class);
                                       iLogout.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                       startActivity(iLogout);
                                       break;

           case R.id.llHomeMenuItem: Log.e(TAG, "Home onClick");
                                     Intent iHome = new Intent(VideoPlayerActivity.this, HomeActivity.class);
                                     iHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                     startActivity(iHome);
                                     break;

           case R.id.llEpgMenuItem: Log.e(TAG, "EPG onClick");
                                    Intent iEpg = new Intent(VideoPlayerActivity.this, EPGActivity.class);
                                    iEpg.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(iEpg);
                                    break;
       }
    }

    void translateView(View view, float value){
        view.animate().translationX(value);
    }
    void translateViewAxisY(View view, float value) { view.animate().translationY(value); }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "MediaPlayer.OnErrorListener:"+what+" "+extra+" "+mp.toString());
        if(what!=(-38))
        Toast.makeText(VideoPlayerActivity.this, "Can't play media", Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        Log.e(TAG, "OnInfoListener:"+mp+" "+what+" "+extra);
        return true;
    }
}
