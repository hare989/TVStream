package com.example.sinan.tvstream.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * Created by Sinan on 11.6.2016.
 */
public class MyVideoView extends VideoView {
    private String TAG = "MyVideoView";
    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged("+w+","+h+","+oldw+","+oldh+")");
    }


}
