package com.example.sinan.tvstream.Customs;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Sinan on 13.5.2016.
 */
public class CustomStringRequest extends StringRequest {
    public CustomStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }
}
