package com.example.sinan.tvstream.Customs;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

/**
 * Created by Sinan on 19.5.2016.
 */
public class CustomJsonArrayRequest extends JsonArrayRequest {
    public CustomJsonArrayRequest(int method, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }
}
