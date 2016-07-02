package com.example.sinan.tvstream.Customs;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.MediaController;

/**
 * Created by Sinan on 13.6.2016.
 */
public class MyMediaController extends MediaController {
    public MyMediaController(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyMediaController(Context context, boolean useFastForward) {
        super(context, useFastForward);
    }

    public MyMediaController(Context context) {
        super(context);
    }


}
