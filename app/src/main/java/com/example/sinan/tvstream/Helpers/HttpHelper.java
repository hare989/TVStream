package com.example.sinan.tvstream.Helpers;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sinan.tvstream.AppMenager;
import com.example.sinan.tvstream.Customs.CustomInputStreamRequest;
import com.example.sinan.tvstream.Customs.CustomJsonArrayRequest;
import com.example.sinan.tvstream.Customs.CustomJsonObjectRequest;
import com.example.sinan.tvstream.Customs.CustomStringRequest;
import com.example.sinan.tvstream.EPGActivity;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.Utils.GsonUtil;
import com.example.sinan.tvstream.Utils.SortingAsyncTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import flexjson.JSONDeserializer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

/**
 * Created by Sinan on 12.5.2016.
 */
public class HttpHelper {

    private HashMap<String, String> responseXml;
    private String postData;
    private JSONObject job;
    private VolleyCallback vcallback;
    private AppMenager app = AppMenager.getInstance();
    private EPG epg1;

    public JSONObject getJob(){return job;}

    public void setResponseXml(HashMap<String, String> responseXml) {
        this.responseXml = responseXml;
    }

    public HashMap<String, String> getResponseXml() {
        return responseXml;
    }

    public JSONObject putJson (String url, JSONObject data, VolleyCallback callback){
        return null;
    }



    public void doRequest (int method, String url, String data, Context context, final VolleyCallback callback){

            this.vcallback = callback;
            this.postData = data;


            CustomStringRequest stringRequest = new CustomStringRequest(method, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) throws JSONException, XmlPullParserException, IOException {
                    callback.onSuccessString(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    callback.onError(error);
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=" +
                            getParamsEncoding();
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                   try {
                       return postData == null? null: postData.getBytes(getParamsEncoding());
                   } catch (UnsupportedEncodingException e) {
                       return null;
                   }
                }
            };

            app.getQueue().add(stringRequest);

           /* StringRequest stringRequest = new StringRequest(method, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response){
                            Log.e("onResponse: ", response);
                            HashMap<String, String> xmlHashMap = XmlParser.parseXmlFromString(response);

                            setResponseXml(xmlHashMap);
                            Log.e("Hash Map length: ", String.valueOf(xmlHashMap.size()));


                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("Volley Error: ", error.getMessage());

                }
            }){
                @Override
                public byte[] getBody() throws AuthFailureError {

                    //String postData = "<request><uuid>358384052229339</uuid><application_publication_id>home_tv_mobile_android</application_publication_id></request>";

                    try {
                        return postData == null ? null :
                                postData.getBytes(getParamsEncoding());
                    } catch (UnsupportedEncodingException uee) {
                        // TODO consider if some other action should be taken
                        return null;
                    }
                }

                @Override
                public String getBodyContentType() {
                    return "application/x-www-form-urlencoded; charset=" +
                            getParamsEncoding();
                }
            };*/




    }

    public void doRequestInJSONObject (int method, String url, JSONObject data,  VolleyCallback callback){
        this.vcallback = callback;
        CustomJsonObjectRequest request = new CustomJsonObjectRequest(method, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) throws JSONException, XmlPullParserException, IOException {
                vcallback.onSuccessJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vcallback.onError(error);
                /*try {
                    String s = error.getMessage();
                    JSONObject obj = new JSONObject(s);
                    setJsonObject(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        });

        request.setTag("JSON request");
        app.getQueue().add(request);

    }

    public void doLogin(JSONObject data,  VolleyCallback callback){
        if(callback!=null) {
            vcallback = callback;
        }
        else{
            vcallback=null;
        }
        CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.POST, app.getLoginUrl(), data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) throws JSONException, XmlPullParserException, IOException {
                        Log.e("doLogin onResponse", response.toString());
                        app.setLoginData(response);
                        if(vcallback!=null) {
                            vcallback.onSuccessJSON(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                     @Override
                     public void onErrorResponse(VolleyError error) {
                        Log.e("doLogin onErrorResponse", error.getMessage());


                         try {
                             JSONObject err = new JSONObject(error.getMessage());
                             if(vcallback!=null) {
                                 vcallback.onError(error);
                             }
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
            });

        app.getQueue().add(request);

    }

    public void getChannelList(String url){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) throws JSONException, XmlPullParserException, IOException {
                Log.e("JSONArray onResponse:", response.toString());
                app.setChannelListTemp(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("channellist onerrorresp", error.toString());

            }
        });

        app.getQueue().add(request);



    }

    public void getEpgJsonVolley(){
        if(app.getIdentify()!=null && (app.getIdentify().getEpgDumpJsonUrl())!=null) {
            CustomJsonObjectRequest request = new CustomJsonObjectRequest(Request.Method.GET, app.getIdentify().getEpgDumpJsonUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) throws JSONException, XmlPullParserException, IOException {
                            if(app.getEpg()==null){
                                String responseString = response.toString();
                                Log.e("volley:deserial. start", String.valueOf(System.currentTimeMillis()));
                                Gson gson = new Gson();
                                epg1 = gson.fromJson(responseString, EPG.class);
                                // epg1 = new JSONDeserializer<EPG>().deserialize(responseString, EPG.class);
                                Log.e("volley:deserial. end", String.valueOf(System.currentTimeMillis()));
                                if(epg1!=null){
                                    app.setEpg(epg1);
                                    SortingAsyncTask sortAsync = new SortingAsyncTask(null);
                                    sortAsync.execute();
                                }
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volley onError", error.toString());
                }
            });
            app.getQueue().add(request);
        }
    }

    public void getEpgJson(){
        if(app.getIdentify()!=null && (app.getIdentify().getEpgDumpJsonUrl())!=null) {

                Log.e("Epg URL", app.getIdentify().getEpgDumpJsonUrl());
                OkHttpClient client = new OkHttpClient();
                okhttp3.Request request = new okhttp3.Request.Builder().url(app.getIdentify().getEpgDumpJsonUrl()).build();

                 client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("OkHttp callback onFail", call.toString()+" "+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, okhttp3.Response response) throws IOException {


                       /* try {*/

                           /* Log.e("OkHttp Callback onRespn", responseString);
                            JSONObject job = new JSONObject(responseString);
                            ObjectMapper mapper = new ObjectMapper();
                            EPG epg = mapper.readValue(responseString, EPG.class);*/
                            if(app.getEpg()==null) {
                                String responseString = response.body().string();

                                Log.e("deserialization start", String.valueOf(System.currentTimeMillis()));

                                epg1 = GsonUtil.deserializeFromJson(responseString, EPG.class);

                                Log.e("deserialization end", String.valueOf(System.currentTimeMillis()));

                                if(epg1!=null) {
                                    Log.e("OkHttp epg.programmes=", String.valueOf(epg1.getProgramme().size()));
                                    if(epg1.getAttributes()==null){
                                        Log.e("okHttp", "getAttributes is null");
                                    }
                                    else{
                                        Log.e("okHttp getAttributees s", "is not null");
                                    }
                                    if(epg1.getProgramme().get(0).getAttributes()==null){
                                        Log.e("OkHttp", "Programe[1].getAttributes is null");
                                    }
                                    else{
                                        Log.e("Programe[1].getAttribut", epg1.getProgramme().get(0).getAttributes().getChannel());
                                    }
                                }else{
                                    Log.e("OkHttp epg","isNull");
                                }
                                app.setEpg(epg1);


                               SortingAsyncTask sortAsync = new SortingAsyncTask(new SortingAsyncTask.SortingAsyncTaskFinished() {
                                   @Override
                                   public void onFinished() {
                                       if(app.getCurrentActivity().getClass()==EPGActivity.class){
                                           EPGActivity s = (EPGActivity) app.getCurrentActivity();
                                           s.initialize();
                                       }
                                   }
                               });
                               sortAsync.execute();
                            }

                           /* Gson gson = new Gson();
                            InputStream is = response.body().byteStream();
                            Log.e("OkHttp","response input stream:"+is.toString());
                            JsonReader jr = new JsonReader(new InputStreamReader(is));
                            jr.beginObject();
                            while(jr.hasNext()){
                                JsonToken jt = jr.peek();
                                Log.e("JSON Token:", jt.toString());
                            }
                            jr.endObject();*/

                            //EPG epg = gson.fromJson(jr, EPG.class);

                       /* } catch (Exception e) {
                            Log.e("OkHttp","JSONException:"+e.getMessage());
                            e.printStackTrace();
                        }*/

                        /* InputStream is = response.body().byteStream();
                        Log.e("OkHttp","response input stream:"+is.toString());
                        JsonReader jr = new JsonReader(new InputStreamReader(is));
                        EPG epg = new EPG();
                        */
                    }
                });


            /*CustomJsonObjectRequest req = new CustomJsonObjectRequest(Request.Method.GET, app.getIdentify().getEpgDumpJsonUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) throws JSONException, XmlPullParserException, IOException {
                            Log.e("EPG Json response:", response.toString());
                            app.setEpgTemp(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("getEpgJson onError:", error.toString());
                }
            });
            app.getQueue().add(req);*/

        }else{ Log.e("app.getIdentify()", "isNull"); }
    }

    public void getEpgZip(String url){

        CustomInputStreamRequest req = new CustomInputStreamRequest(Request.Method.GET, url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) throws JSONException, XmlPullParserException, IOException {
               Log.e("Environment.getDataDir", Environment.getDataDirectory().toString());
                Log.e("Environment.getRootDir", Environment.getRootDirectory().toString());
                Log.e("Response lenght:", String.valueOf(response.length));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        app.getQueue().add(req);
    }

    void setJsonObject(JSONObject json){
        job = json;
    }
    JSONObject getJsonObject () { return job; }

   /* @Override
    public void onErrorResponse(VolleyError error) {
        callback.onError(error);
        Log.e("vOLLEY ERROR: ", error.getMessage());
    }

    @Override
    public void onResponse(Object response){

        try {
            Log.e("Response object", response.toString());
            callback.onSuccessJSON((JSONObject) response);
        } catch (Exception e) {
            callback.onSuccessString((String) response);
        }

    }*/

    public interface VolleyCallback{
        void onSuccessJSON(JSONObject result);
        void onSuccessString(String result);
        void onError(VolleyError error);
    }


}
