package com.example.sinan.tvstream.Customs;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Sinan on 12.5.2016.
 */
public class CustomJsonObjectRequest extends JsonObjectRequest {
    public CustomJsonObjectRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        if(volleyError!=null && volleyError.networkResponse != null && volleyError.networkResponse.data!=null){
            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
            volleyError = error;
        }
        return volleyError;
    }
}
