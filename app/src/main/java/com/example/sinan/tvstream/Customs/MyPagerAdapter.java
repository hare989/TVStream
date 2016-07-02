package com.example.sinan.tvstream.Customs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.sinan.tvstream.AppMenager;
import com.example.sinan.tvstream.Model.Identify;

import java.nio.channels.Channel;
import java.util.List;

/**
 * Created by Sinan on 30.5.2016.
 */
public class MyPagerAdapter  extends PagerAdapter {
    private List<com.example.sinan.tvstream.Model.Channel> channels;
    private AppMenager app;

    public MyPagerAdapter() {
        this.app = AppMenager.getInstance();
        this.channels = app.getListOfChannels();
    }

    @Override
    public int getCount() {
        return this.channels.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==((View)object));
    }

    @Override
    public Object instantiateItem(View container, int position) {
        Log.e("instantiateItem", container.toString()+" "+position);
        return this.channels.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
