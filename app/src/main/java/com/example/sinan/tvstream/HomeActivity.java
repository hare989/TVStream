package com.example.sinan.tvstream;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sinan.tvstream.Customs.PagerContainer;
import com.example.sinan.tvstream.Fragments.channelPage;
import com.example.sinan.tvstream.Model.Channel;

public class HomeActivity extends FragmentActivity implements View.OnClickListener {
    private ViewPager channelViewPager;
    private AppMenager app = AppMenager.getInstance();
    private PagerContainer mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        channelViewPager  = (ViewPager) findViewById(R.id.channelViewPager);
        FragmentManager fm = getSupportFragmentManager();
        channelViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                Fragment fragment = new channelPage();
                Bundle args = new Bundle();
                args.putInt("channelPosition", position);
                Channel channel=app.getListOfChannels().get(position);
                args.putSerializable("channel", channel);

                fragment.setArguments(args);

                return fragment;
            }

            @Override
            public int getCount() {
                if(app.getListOfChannels()!=null) {
                    return app.getListOfChannels().size();
                }
                else{
                    return 0;
                }
            }
        });

        Button btnEPG = (Button) findViewById(R.id.btnEPG);
        btnEPG.setOnClickListener(this);



       // channelViewPager = (ViewPager) findViewById(R.id.channelViewPager);
      /*  mContainer = (PagerContainer) findViewById(R.id.pager_container);
        channelViewPager = mContainer.getViewPager();
        PagerAdapter adapter = new MyPagerAdapter();
        channelViewPager.setAdapter(adapter);
        channelViewPager.setOffscreenPageLimit(adapter.getCount());
        channelViewPager.setClipChildren(false);*/

       /* ViewPager myVP=new ViewPager(this);
        myVP.setId(R.id.channelViewPager);
        LinearLayout linL = (LinearLayout) findViewById(R.id.contentOfChannelViewPager);
        linL.addView(myVP);*/

        /*ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        android.app.FragmentManager fm = getFragmentManager();
        android.app.FragmentTransaction ft =  fm.beginTransaction();
        ft.*/

        /*PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return app.getListOfChannels().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return false;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }
        };
        channelViewPager.setAdapter(adapter);*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(app.getEpg()!=null){
            Log.e("EPG JE VELAK:", String.valueOf(app.getEpg().getProgramme().size()));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(app.getEpg()!=null){
            Log.e("EPG JE VELAK:", String.valueOf(app.getEpg().getProgramme().size()));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnEPG:   Intent i = new Intent(app.getInstance(), EPGActivity.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                break;
            case R.id.btnArhivaSadrzaja: break;
            default: break;
        }
    }
}
