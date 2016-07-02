package com.example.sinan.tvstream;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.example.sinan.tvstream.Helpers.HttpHelper;

public class MainActivity extends Activity {

    public static final String REQUEST_TAG = "CustomVolleyJsonRequest";
    private RequestQueue mQueue;
    private AppMenager app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = AppMenager.getInstance();
        HttpHelper httpHelper = new HttpHelper();

        app.setCurrentActivity(this);


       /* HttpHelper httpHelper = new HttpHelper();
        JSONObject requestData = null;
        try {
            requestData = XML.toJSONObject("<uuid>358384052229339</uuid><application_publication_id>home_tv_mobile_android</application_publication_id>");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        httpHelper.doRequestInJSONObject(Request.Method.PUT, "http://stg-hrt.spectar.tv/client_api.php/config/identify/format/json",
                    requestData, this, new HttpHelper.VolleyCallback() {
                    @Override
                    public void onSuccessJSON(JSONObject result) {
                        Log.e("JSON Response length: ", String.valueOf(result.length()));
                    }

                    @Override
                    public void onSuccessString(String result) {

                    }

                    });

        httpHelper.doRequest(Request.Method.PUT, "http://stg-hrt.spectar.tv/client_api.php/config/identify",
                "<request><uuid>358384052229339</uuid><application_publication_id>home_tv_mobile_android</application_publication_id></request>", this,
                new HttpHelper.VolleyCallback() {
                    @Override
                    public void onSuccessJSON(JSONObject result) {

                    }

                    @Override
                    public void onSuccessString(String result) {
                        HashMap<String, String> xmlResult = XmlParser.parseXmlFromString(result);
                        JSONObject jsonFromHashMap = new JSONObject(xmlResult);
                        Log.e("JSON Object from xml: ", String.valueOf(jsonFromHashMap.length()));
                    }
                });*/
        //txtView.setText(hm.size());


    }

    @Override
    protected void onStart() {
        super.onStart();

       /* try {
            mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext()).getRequestQueue();
            String url = "http://stg-hrt.spectar.tv/client_api.php/config/identify/format/json";
            JSONObject requestData =  XML.toJSONObject("<uuid>358384052229339</uuid><application_publication_id>home_tv_mobile_android</application_publication_id>");
            CustomJsonObjectRequest jsonRequest = new CustomJsonObjectRequest(Request.Method.POST, url, requestData, this, this);
            jsonRequest.setTag(REQUEST_TAG);
            mQueue.add(jsonRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        /*if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }*/
    }

}
