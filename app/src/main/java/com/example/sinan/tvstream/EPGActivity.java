package com.example.sinan.tvstream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sinan.tvstream.Customs.CustomRecyclerAdapter;
import com.example.sinan.tvstream.Customs.CustomVolleyRequestQueue;
import com.example.sinan.tvstream.Customs.MyChannelsArrayAdapter;
import com.example.sinan.tvstream.Customs.MyDateArrayAdapter;
import com.example.sinan.tvstream.Customs.MyProgrammeArrayAdapter;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.Model.Identify;
import com.example.sinan.tvstream.Utils.IconLoaderAsync;
import com.example.sinan.tvstream.Utils.PrefsUtil;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class EPGActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private String TAG = "EPGActivity";
    private AppMenager app = AppMenager.getInstance();
    private List<Channel> channels;
    private Channel selectedChannel;
    private List<EPG.Programme> channelProgrammes;
    private List<Channel> listOfChannels;
    private List<Date> dateRangeList;
    private ListView lvChannelProgrammes;
    private MyProgrammeArrayAdapter adapter;
    private MyDateArrayAdapter datesAdapter;
    private MyChannelsArrayAdapter channelChooserAdapter;
    private RecyclerView recyChannelEPG;
    private RecyclerView.LayoutManager layoutManager;
    private Toolbar myToolbar;
    private Spinner spinChannels;
    private Spinner spinDates;
    private ListPopupWindow popupWindow;
    private NetworkImageView imgBtnChooseChannel;
    private ImageButton imgBtnPlayChannel;
    private ImageLoader imgLoader;
    private LinearLayout llCustomAppMenu;
    private boolean isInitialized = false;
    private ProgressDialog progressDialog;
    private LinearLayout llMenu;
    private LinearLayout llLogoutMenuItem;
    private boolean isMenuAnimated=false;
    private ImageButton imgBtnMenu;
    private boolean showingProgrammeDetails = false;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epg);
        app.setCurrentActivity(this);

        if(app.isSortedEPG()){
            initialize();
        }
        else{
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setTitle("Retrieving data");
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(app.getListOfChannels()!=null){
            Log.e(TAG, "onResume list of Channels is NOT null");
            if(app.isSortedEPG()){
                Log.e(TAG, "EPG is sorted!");
                if(!isInitialized){
                    initialize();
                }

            }else{
                Log.e(TAG, "onResume EPG NOT sorted");
            }

        }else{
            Log.e(TAG, "onResume list of Channels is null");
        }
    }

    public void initialize(){
        if(app.getListOfChannels()!=null){
            Log.e(TAG, "list of Channels is NOT null");

            listOfChannels = app.getListOfChannels();
            selectedChannel = listOfChannels.get(0);

            imgLoader = CustomVolleyRequestQueue.getInstance(this).getImageLoader();

            changeCurrentProgrammeImage();
            updateDateRangeList();

            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


            llCustomAppMenu = (LinearLayout) findViewById(R.id.llCustomAppMenu);
            llMenu = (LinearLayout) findViewById(R.id.llMenu);
            llLogoutMenuItem = (LinearLayout) llMenu.findViewById(R.id.llLogoutMenuItem);
            llLogoutMenuItem.setOnClickListener(this);

            imgBtnMenu = (ImageButton) findViewById(R.id.imgBtnMenu);
            imgBtnMenu.setOnClickListener(this);

            imgBtnPlayChannel = (ImageButton) findViewById(R.id.imgBtnPlayChannel);
            imgBtnPlayChannel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selectedChannel!=null){
                        Intent i = new Intent(EPGActivity.this, VideoPlayerActivity.class);
                        i.putExtra("channel", selectedChannel);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                    }
                }
            });

            spinDates = (Spinner) findViewById(R.id.spinDates);
            imgBtnChooseChannel = (NetworkImageView) findViewById(R.id.imgBtnChooseChannel);
            channelChooserAdapter = new MyChannelsArrayAdapter(EPGActivity.this, R.layout.channel_spinner_row, app.getListOfChannels());

            popupWindow = new ListPopupWindow(EPGActivity.this);
            popupWindow.setAnchorView(imgBtnChooseChannel);
            popupWindow.setAdapter(channelChooserAdapter);

           /* popupWindow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedChannel = listOfChannels.get(position);
                    Log.e(TAG, "Channel Chooser onItemSelected "+selectedChannel.getName());
                    imgBtnChooseChannel.setImageUrl(selectedChannel.getImg(), imgLoader);
                    updateDateRangeList();
                    popupWindow.dismiss();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/

            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedChannel = listOfChannels.get(position);
                    Log.e(TAG, "Channel Chooser onItemClick "+selectedChannel.getName());
                    changeCurrentProgrammeImage();
                    imgBtnChooseChannel.setImageUrl(selectedChannel.getImg(), imgLoader);
                    updateDateRangeList();
                    channelProgrammes = selectedChannel.getProgrammesByDate(dateRangeList.get(spinDates.getSelectedItemPosition()));
                    adapter.refreshData(channelProgrammes);
                    popupWindow.dismiss();
                }
            });

            imgBtnChooseChannel.setImageUrl(listOfChannels.get(0).getImg(), imgLoader);
            imgBtnChooseChannel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "imgBtnChnnelsChooser onClick");

                    if (!popupWindow.isShowing()) {
                        popupWindow.setWidth(imgBtnChooseChannel.getWidth());
                        popupWindow.show();
                    } else {
                        popupWindow.dismiss();
                    }

                }
            });

            /*Spinner spinChnnels = (Spinner) findViewById(R.id.spinChannels);
            spinChnnels.setAdapter(channelChooserAdapter);
            spinChnnels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selectedChannel = listOfChannels.get(position);
                    Log.e(TAG, "Channel Spinner onItemSelected "+selectedChannel.getName());
                    updateDateRangeList();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/




            /*popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    selectedChannel = listOfChannels.get(position);
                    Log.e(TAG, "Channel Chooser onItemClick "+selectedChannel.getName());
                    imgBtnChooseChannel.setImageUrl(selectedChannel.getImg(), imgLoader);
                    changeCurrentProgrammeImage();
                    updateDateRangeList();
                    popupWindow.dismiss();
                }
            });*/



            /*spinChannels = (Spinner) findViewById(R.id.spinChannels);
            Log.e(TAG, "spinChannels " + spinChannels.getId() + " " + spinChannels.toString());
            MyChannelsArrayAdapter channelsAdapter = new MyChannelsArrayAdapter(this, R.layout.channel_spinner_row, app.getListOfChannels());
            spinChannels.setAdapter(channelsAdapter);
            spinChannels.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("spinChannel", "onItemSelected");
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    Log.e("spinChannel", "onNothingSelected");
                }
            });*/


            spinDates.setAdapter(datesAdapter);
            spinDates.setSelection(2);
            spinDates.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Log.e("spinDates", "onItemSelected:"+dateRangeList.get(position));
                    //Log.e(TAG, "channelProgrammes size:"+selectedChannel.getProgrammes().size());
                    channelProgrammes = selectedChannel.getProgrammesByDate(dateRangeList.get(position));
                    Log.e(TAG, "channelProgrammes size:"+selectedChannel.getProgrammes().size());
                    Log.e(TAG, "channelProgrammes size:"+channelProgrammes.size());
                    adapter.refreshData(channelProgrammes);

                    Log.e(TAG, "channelProgrammes size after refreshData:"+selectedChannel.getProgrammes().size());
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    Log.e("spinDates", "onNothingSelected");
                }
            });

            //channelProgrammes = selectedChannel.getProgrammes()==null? null:selectedChannel.getProgrammes();
            lvChannelProgrammes = (ListView) findViewById(R.id.lvChannelProgramme);
            adapter = new MyProgrammeArrayAdapter(this, R.layout.channel_programmes_list_item, channelProgrammes);
            lvChannelProgrammes.setAdapter(adapter);
            lvChannelProgrammes.setOnItemClickListener(this);

           /* lvChannelProgrammes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.e(TAG, "Channel Programmes List onItemSelected:"+channelProgrammes.get(position).getTitle() );
                    PopupWindow popupProgrammeDetails = new PopupWindow(200, 200);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/

            if(app.isSortedEPG() && channelProgrammes!=null){
                Log.e(TAG, "in setListOfProgrammes programmes size="+channelProgrammes.size());

            }else{
                Log.e(TAG, "onCreate EPG NOT sorted");
            }

            if(progressDialog!=null && progressDialog.isShowing()){
                progressDialog.dismiss();
                progressDialog=null;
            }
            isInitialized = true;

        }else{
            Log.e(TAG, "list of Channels is null");
        }
    }

    @Override
    public void onClick(View v) {

        Log.e(TAG, "onClick: "+v);

        switch (v.getId()){
            case R.id.llLogoutMenuItem: Log.e(TAG, "Logout onClick");
                                        PrefsUtil.removeFromPrefs(PrefsUtil.PREFS_LOGIN_USER_DATA_KEY);
                                        Intent i = new Intent(EPGActivity.this, LoginActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        break;

            case R.id.imgBtnMenu: Log.e(TAG, "Menu onClick");
                                     /*if(isMenuAnimated){
                                         Log.e(TAG, "PlayerMenu onClick clearAnimation");
                                         translateView(llMenu, llMenu.getWidth());
                                         Log.e(TAG, "on Animate x:"+llMenu.getX()+" y:"+llMenu.getY());
                                     }else {
                                         Log.e(TAG, "PlayerMenu onClick animate");
                                         translateView(llMenu, -llMenu.getWidth());
                                         Log.e(TAG, "on clear Animation x:"+llMenu.getX()+" y:"+llMenu.getY());
                                     }

                                     isMenuAnimated = !isMenuAnimated;*/
                                    Log.e( TAG, "Drawer Layout "+drawerLayout);
                                    if(!drawerLayout.isDrawerOpen(GravityCompat.END))
                                       drawerLayout.openDrawer(GravityCompat.END);
                                    else
                                        drawerLayout.closeDrawer(GravityCompat.END);
        }


    }

    void translateView(View view, float value){
        view.animate().translationX(value);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(TAG, "AdapterView.OnItemClick: "+view+" "+parent+" "+position);
        if (!showingProgrammeDetails) {
            showingProgrammeDetails = true;
            EPG.Programme selectedProg = channelProgrammes.get(position);

            View detailsView = getDetailsView(selectedProg);

            final PopupWindow popupProgrammeDetails = new PopupWindow(detailsView);
            popupProgrammeDetails.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
            popupProgrammeDetails.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                popupProgrammeDetails.setOverlapAnchor(true);
            }
            ImageButton imgBtnClose = (ImageButton) detailsView.findViewById(R.id.imgBtnClose);
            imgBtnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupProgrammeDetails.dismiss();
                    showingProgrammeDetails = false;
                }
            });


            popupProgrammeDetails.setAnimationStyle(R.style.popupAnimation);
            popupProgrammeDetails.showAsDropDown(llCustomAppMenu);
        }


    }

    private View getDetailsView(EPG.Programme selectedProg) {
        View view = getLayoutInflater().inflate(R.layout.programme_details, null);
        TextView txtProgrammeTitle = (TextView) view.findViewById(R.id.txtProgrammeTitleDetail);
        TextView txtProgrammeCategory = (TextView) view.findViewById(R.id.txtProgrammeCategoryDetail);
        TextView txtProgrammeDesc = (TextView) view.findViewById(R.id.txtProgrammeDescriptionDetail);
        NetworkImageView imgProgramme = (NetworkImageView) view.findViewById(R.id.imgProgrammeDetail);

        imgProgramme.setImageUrl(selectedProg.getIcon().getAttributes().getSrc(), imgLoader);
        txtProgrammeTitle.setText(selectedProg.getTitle());
        txtProgrammeCategory.setText(selectedProg.getCategory());
        txtProgrammeDesc.setText(selectedProg.getDesc());
        txtProgrammeDesc.setMovementMethod(new ScrollingMovementMethod());

        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.miSettings: Log.e(TAG, "Menu Item Settings");break;
        }
        return super.onOptionsItemSelected(item);
    }

    void updateDateRangeList(){
        if(dateRangeList!=null){
            dateRangeList = null;
        }
        dateRangeList = new ArrayList<Date>();
        dateRangeList = selectedChannel.getProgrammesDateRange();
        if(datesAdapter!=null){
            datesAdapter.refreshData(dateRangeList);
        }
        else {
            datesAdapter = new MyDateArrayAdapter(this, R.layout.channel_spinner_row, dateRangeList);
        }

        Log.e(TAG, "in updateDateRangeList after create datesAdapter dateRangeList.size()="+dateRangeList.size());
    }

    private void changeCurrentProgrammeImage(){
        NetworkImageView imgCurrentProgramme = (NetworkImageView) findViewById(R.id.imgCurrentProgramme);
        imgCurrentProgramme.setImageUrl(selectedChannel.getCurrentProgramme().getIcon().getAttributes().getSrc(), imgLoader);
        TextView txtCurrentProgrammeTitle = (TextView) findViewById(R.id.txtCurrentProgrammeTitle);
        TextView txtCurrentProgrammeStart = (TextView) findViewById(R.id.txtCurrentProgrammeStart);
        TextView txtCurrentProgrammeStop = (TextView) findViewById(R.id.txtCurrentProgrammeStop);
        ProgressBar pbCurrentProgrammeDuration = (ProgressBar) findViewById(R.id.pbCurrentProgrammeDuration);

        txtCurrentProgrammeTitle.setText(selectedChannel.getCurrentProgramme().getTitle());
        txtCurrentProgrammeStart.setText(selectedChannel.getCurrentProgramme().getAttributes().getStartSubstring());
        txtCurrentProgrammeStop.setText(selectedChannel.getCurrentProgramme().getAttributes().getStopSubstring());
        pbCurrentProgrammeDuration.setProgress(selectedChannel.getCurrentProgramme().getTimeElapsed());
    }

}
