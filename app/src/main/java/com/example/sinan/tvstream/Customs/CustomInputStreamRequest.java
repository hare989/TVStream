package com.example.sinan.tvstream.Customs;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Sinan on 21.5.2016.
 */
public class CustomInputStreamRequest extends Request<byte[]> {

    private Response.Listener<byte[]> mListener;

    public  CustomInputStreamRequest(int httpMethod, String url, Response.Listener<byte[]> listener, Response.ErrorListener errorListener){
        super(httpMethod, url, errorListener);
        setShouldCache(false);
        mListener=listener;
    }
    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {

        Log.e("CustInputStreamRequest", "in parseNetworkResponse response="+response.toString());
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(byte[] response) throws IOException, XmlPullParserException, JSONException {
        mListener.onResponse(response);
    }
}
