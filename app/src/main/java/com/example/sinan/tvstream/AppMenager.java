package com.example.sinan.tvstream;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.sinan.tvstream.Customs.CustomVolleyRequestQueue;
import com.example.sinan.tvstream.Helpers.HttpHelper;
import com.example.sinan.tvstream.Helpers.SecurityUtil;
import com.example.sinan.tvstream.Model.CurrentEPG;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.Model.Identify;
import com.example.sinan.tvstream.Model.UserLogin;
import com.example.sinan.tvstream.Utils.GsonUtil;
import com.example.sinan.tvstream.Utils.PrefsUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinan on 13.5.2016.
 */
public class AppMenager extends Application {
    private String TAG = "AppMenager";
    public static String baseUrl = "http://prd-hrt.spectar.tv/client_api.php";
    public static String identifyUrl = baseUrl+"/config/identify";
    public static String forResponseInJson="/format/json";
    public static String loginUrl=baseUrl+"/user/login/session_id/";
    public static String identifyUrl_JsonResponse = identifyUrl+forResponseInJson;
    public static String channelListUrl=baseUrl+"/channel/list/session_id/";

    private RequestQueue queue;

    private String access_token;
    private String session_id;
    private String deviceUUID;
    private String jsonStringIdentifyData = "{'uuid':'3251535', 'application_publication_id':'home_tv_mobile_android'}";
    private JSONObject jsonIdentifyData;
    private JSONObject jsonResponse;
    private JSONObject identifyData;
    private JSONObject loginData;
    private JSONObject realLoginData;
    private JSONArray channelListTemp;
    private JSONArray channelList;
    private JSONObject EpgTemp;
    private VolleyError err;
    private boolean badRequest = false;
    private boolean needToRequest = false;
    private boolean isIdentifyed = false;
    private boolean isSortedEPG;
    private Integer statusCode;
    private Identify identify;
    private List<com.example.sinan.tvstream.Model.Channel> listOfChannels;
    private UserLogin userLoginData;
    private EPG epg;
    private Activity currentActivity;

    public String getLoginUrl(){ return loginUrl; }
    public RequestQueue getQueue() {
        return queue;
    }
    public JSONObject getJsonResponse() {
        return jsonResponse;
    }
    public JSONObject getIdentifyData(){ return identifyData; }
    public JSONObject getLoginData(){ return loginData; }
    public JSONObject getRealLoginData() { return realLoginData; }
    public JSONArray getChannelListTemp(){ return channelListTemp; };
    public JSONArray getChannelList(){ return channelList; };
    public Identify getIdentify(){ return  this.identify; }
    public List<com.example.sinan.tvstream.Model.Channel> getListOfChannels() { return listOfChannels;}
    public UserLogin getUserLoginData() { return userLoginData; }
    public JSONObject getEpgTemp() { return this.EpgTemp; }
    public EPG getEpg(){ return this.epg; }
    public boolean isSortedEPG() { return isSortedEPG; }
    public Activity getCurrentActivity() {
        return currentActivity;
    }

    private void setJsonResponse(JSONObject response){ jsonResponse = response; }
    public void setLoginData(JSONObject data){ loginData = data; }
    public void setChannelListTemp(JSONArray channelList){ channelListTemp = channelList;}
    public void setChannelList(JSONArray channelList){ this.channelList = channelList;}
    public void setIdentify(Identify identify){ this.identify = identify;}
    public void setListOfChannels(List<com.example.sinan.tvstream.Model.Channel> listOfChannels) { this.listOfChannels = listOfChannels;}
    public void setUserLoginData(UserLogin userLoginData) { this.userLoginData = userLoginData; }
    public void setEpgTemp (JSONObject epgJson){ this.EpgTemp = epgJson; }
    public void setEpg(EPG epg){ this.epg = epg; }
    public void setSortedEPG(boolean sortedEPG) { isSortedEPG = sortedEPG; }
    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    private static AppMenager sInstance;
    private HttpHelper httpHelper;

    public static AppMenager getInstance(){
        return sInstance;
    }

    public com.example.sinan.tvstream.Model.Channel getChannelById(String id){
        com.example.sinan.tvstream.Model.Channel result=null;
        for(com.example.sinan.tvstream.Model.Channel ch: getListOfChannels()) {
            if (ch.getId().equals(id)) {
                result=ch;
            }
        }
        return  result;
    }

    public void logiraj(){


        try {
            Gson gson = new Gson();
            JSONObject job= new JSONObject(gson.toJson(getEpg()));
            if(job!=null) {
                FileWriter writer = new FileWriter(Environment.getExternalStorageDirectory()+"/EPG.json");
                writer.write(job.toString());
                writer.flush();
                writer.close();
                Log.e("Logiraj", "After writing to file.");
            }else{
                Log.e("Logiranje job-a:", "isNull");
            }
            for(int i=0; i<5; i++) {

            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance=this;
        queue = CustomVolleyRequestQueue.getInstance(sInstance).getRequestQueue();
            httpHelper = new HttpHelper();
            jsonIdentifyData = createJsonIdentifyData();

            jsonResponse=null;
            identify(Request.Method.PUT);


        getQueue().addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public void onRequestFinished(Request<Object> request) {
                try {
                    Log.e("onRequestFinished", request.toString());
                    if(getJsonResponse()!=null){
                        Log.e("JSONrespons onReqFinish", getJsonResponse().toString());
                        if(!getJsonResponse().isNull("error")){
                            if(getJsonResponse().has("error")) {
                                if(getJsonResponse().getString("error").isEmpty()){
                                    Log.e("bez error objekta", getJsonResponse().getString("uuid")+"="+jsonIdentifyData.getString("uuid"));
                                    identify(Request.Method.PUT);
                                }
                                else if(getJsonResponse().getJSONObject("error").has("@attributes")) {
                                    Integer statusCode = getJsonResponse().getJSONObject("error").getJSONObject("@attributes").getInt("status");
                                    Log.e("Status code", statusCode.toString());
                                    switch (statusCode){
                                        case 0:
                                                    Identify identify = GsonUtil.deserializeFromJson(getJsonResponse().toString(), Identify.class);
                                                    setIdentify(identify);
                                                    setJsonResponse(null);

                                                    httpHelper.getEpgJson();

                                                    session_id=getIdentify().getSessionId();
                                                    loginUrl+=session_id+forResponseInJson;

                                                    String loginDataString =  PrefsUtil.getFromPrefs(PrefsUtil.PREFS_LOGIN_USER_DATA_KEY);
                                                    if(loginDataString.isEmpty()){
                                                        Log.e(TAG, "login prefs are Empty. Starting LoginActivity");

                                                        Intent i = new Intent(sInstance, LoginActivity.class);
                                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(i);
                                                    }
                                                    else{
                                                        Log.e(TAG, "login prefs are set:"+loginDataString);
                                                        JSONObject loginData = new JSONObject(loginDataString);

                                                        httpHelper.doLogin(loginData,null);
                                                    }


                                                 break;

                                        case 4:

                                        default: Log.e("switch default:statCode", statusCode.toString()); break;

                                    }
                                }else {
                                    Integer statusCode = getJsonResponse().getJSONObject("error").getInt("status");
                                    Log.e("error.status", statusCode.toString());
                                    switch (statusCode){
                                        case 5: identify(Request.Method.POST); break;
                                    }
                                }
                            }
                        }else {
                            Log.e("JsonResponse.error","is null");
                        }
                    }else if(getLoginData()!=null){
                        Log.e("Login data:", getLoginData().toString());
                        if(!getLoginData().isNull("session_token")){

                            String stringLoginData = getLoginData().toString();

                            UserLogin userLogin = GsonUtil.deserializeFromJson(stringLoginData, UserLogin.class);
                            setUserLoginData(userLogin);
                            setLoginData(null);

                            downloadChannels();

                        }

                    }if(getChannelListTemp()!=null){
                        try {
                            Gson gson = new Gson();
                            List<com.example.sinan.tvstream.Model.Channel> list = new ArrayList<com.example.sinan.tvstream.Model.Channel>();
                            Type listType = new TypeToken<List<com.example.sinan.tvstream.Model.Channel>>(){}.getType();
                            list = GsonUtil.deserializeFromJson(getChannelListTemp().toString(), listType);
                            setListOfChannels(list);
                            setChannelListTemp(null);

                            Log.e("List of Channels", getListOfChannels().toString());

                            String currentEpgUri = getIdentify().getModules().getEpg().getResources().getList().getUri();
                            currentEpgUri+="/session_id/"+session_id+"/access_token/"+access_token+"/current/true"+forResponseInJson;
                            Log.e("Current EPG Uri:", currentEpgUri);
                            httpHelper.doRequestInJSONObject(Request.Method.GET, currentEpgUri, null, new HttpHelper.VolleyCallback() {
                                @Override
                                public void onSuccessJSON(JSONObject result) {

                                    try {
                                        Log.e("getCurrentEpg json:", result.toString());

                                            JSONArray programs = result.getJSONArray("programme");
                                        Log.e("SIze of programme", String.valueOf(programs.length()));
                                           /* for( int i=0; i< programs.length(); i++){
                                                Log.e("in for loop","af_As.ć");
                                                if(programs.getJSONObject(i).getJSONObject("credits").has("actor")){
                                                    Object o = programs.getJSONObject(i).getJSONObject("credits").get("actor");
                                                    if(o instanceof String) {
                                                        Log.e("Actor", programs.getJSONObject(i).getJSONObject("credits").getString("actor"));
                                                        JSONArray actors = new JSONArray();
                                                        String actor = programs.getJSONObject(i).getJSONObject("credits").getString("actor");
                                                        Log.e("Actor je", actor);
                                                        actors.put(actor);
                                                        Log.e("actors array", actors.toString());
                                                        programs.getJSONObject(i).getJSONObject("credits").remove("actor");
                                                        programs.getJSONObject(i).getJSONObject("credits").put("actor", actors);
                                                        Log.e("actors added", programs.getJSONObject(i).getJSONObject("credits").getJSONArray("actor").toString());
                                                    }

                                                }
                                                if(programs.getJSONObject(i).getJSONObject("credits").getString("director")!=null){
                                                    Object o = programs.getJSONObject(i).getJSONObject("credits").get("director");
                                                    if(o instanceof String) {
                                                        Log.e("DIrector", programs.getJSONObject(i).getJSONObject("credits").getString("director"));
                                                        JSONArray directors = new JSONArray();
                                                        String director = programs.getJSONObject(i).getJSONObject("credits").getString("director");
                                                        Log.e("director je", director);
                                                        directors.put(director);
                                                        Log.e("directors array", directors.toString());
                                                        programs.getJSONObject(i).getJSONObject("credits").remove("director");
                                                        programs.getJSONObject(i).getJSONObject("credits").put("director", directors);
                                                        Log.e("directors added", programs.getJSONObject(i).getJSONObject("credits").getJSONArray("director").toString());
                                                    }
                                                }
                                            }*/

                                        CurrentEPG currentEpg = GsonUtil.deserializeFromJson(result.toString(), CurrentEPG.class);
                                        if(currentEpg!=null){
                                            for(CurrentEPG.Programme prog : currentEpg.getProgramme()){
                                                com.example.sinan.tvstream.Model.Channel ch =getChannelById(prog.getAttributes().getChannel());
                                                if(ch!=null) {
                                                    Log.e("AppMenager", "getChannelById=" + ch.getName());
                                                    ch.setCurrentProgramme(prog);
                                                }
                                            }
                                        }
                                        else{
                                            Log.e("AppMenager", "CurrentEpg is null!");
                                        }


                                        startHomeActivity();

                                    } catch (JSONException e) {
                                        Log.e("AppMenager", "onSuccess getCurrentEPG: JSONExeption");
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onSuccessString(String result) {

                                }

                                @Override
                                public void onError(VolleyError error) {
                                    Log.e("getCurrentEpg json:", "onError"+err.toString());
                                }
                            });

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }if(getEpgTemp()!=null){
                        Gson gson = new Gson();
                        Log.e("Polazno vrijeme:", String.valueOf(System.currentTimeMillis()));
                        EPG epg = gson.fromJson(getEpgTemp().toString(), EPG.class);
                        Log.e("Prolazno vrijeme:", String.valueOf(System.currentTimeMillis()));
                        Log.e("EPG Object:", String.valueOf(epg.getProgramme().size()));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private JSONObject createJsonIdentifyData() {
        JSONObject job = new JSONObject();
        deviceUUID = obtainDeviceUUID();
        try {
            job.put("uuid", deviceUUID);
            job.put("application_publication_id", "home_tv_mobile_android");
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            return job;
        }
    }

    private void identify(int httpMethod) {

        httpHelper.doRequestInJSONObject(httpMethod, identifyUrl_JsonResponse, jsonIdentifyData, new HttpHelper.VolleyCallback() {
            @Override
            public void onSuccessJSON(JSONObject result){
                Log.e("onSuccessJSON",result.toString());
                setJsonResponse(result);
            }

            @Override
            public void onSuccessString(String result) {

            }

            @Override
            public void onError(VolleyError error) {
                try {
                    Log.e("onError volley", error.toString()+" "+error.getMessage());

                    if (error instanceof TimeoutError) {
                        Log.e("identify onError","TimeoutError "+error.toString());
                        identify(Request.Method.PUT);
                    }else if(error instanceof NoConnectionError){
                        Log.e("identify onError","NoConnectionError:"+error.toString());

                        //showAlertDialog();
                    }
                    else if (error instanceof AuthFailureError) {
                        Log.e("identify onError","AuthFailure:"+error.toString());
                    } else if (error instanceof ServerError) {
                        Log.e("identify onError","ServerError:"+error.toString());
                    } else if (error instanceof NetworkError) {
                        Log.e("identify onError","NetworkError:"+error.toString());
                        String str = error.getMessage();
                        JSONObject job = new JSONObject(str);
                        setJsonResponse(job);
                    } else if (error instanceof ParseError) {
                        Log.e("identify onError","ParseError:"+error.toString());
                    }else{
                        JSONObject err = new JSONObject(error.getMessage());
                        if(err.getJSONObject("error")!=null){
                            Integer statusCode = err.getJSONObject("error").getInt("status");
                            switch (statusCode){
                                case 5: identify(Request.Method.POST); err=null; error=null; break;
                            }
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("No Internet");
        builder.setMessage("Internet is required. Please Retry.");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                System.exit(0);
            }
        });

        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                identify(Request.Method.PUT);
            }
        });
        AlertDialog dialog = builder.create(); // calling builder.create after adding buttons
        dialog.show();
    }

    private void handleResponse(JSONObject jsonResponse) {
        try {
            if(jsonResponse!=null){
                if(jsonResponse.has("error")){
                    Log.e("JSONObject error","");
                    if (jsonResponse.getJSONObject("error").has("@attributes")) {
                        Log.e("error.attributes","");
                        Integer statusCode = jsonResponse.getJSONObject("error").getJSONObject("@attributes").getInt("status");
                        switch (statusCode){
                            case 0: Log.e("Status",statusCode.toString()+"OK"); break;
                        }
                    }else {
                        Log.e("JSONObject error","bez attr");
                        Integer statusCode = jsonResponse.getJSONObject("error").getInt("status");
                        switch (statusCode){
                            case 5: identify(Request.Method.POST); break;
                        }
                    }
                }else if(!jsonResponse.isNull("error")){
                            if(jsonResponse.getString("error").isEmpty() && jsonResponse.getString("uuid").equals(deviceUUID)){
                                identify(Request.Method.PUT);
                            }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String obtainDeviceUUID() {
        TelephonyManager telephonyManager = (TelephonyManager) this.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String uuid = telephonyManager.getDeviceId();
        Log.e(TAG, "UUID="+uuid);
        return uuid;
    }

    private void startHomeActivity(){
        Intent i = new Intent(sInstance, HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void downloadChannels(){
        if(getUserLoginData()!=null) {
            String sessionToken = getUserLoginData().getSessionToken();
            access_token = SecurityUtil.computeSHAHash(deviceUUID + sessionToken);
            //access_token je vec postavljen
            Log.e("sessio_token:", getUserLoginData().getSessionToken());
            Log.e("sessiod_id&access_token", session_id + " " + access_token);
            String link = channelListUrl + session_id + "/access_token/" + access_token + forResponseInJson;
            Log.e("link je", link);

            httpHelper.getChannelList(link);
        }else{
            Log.e(TAG, "downloadChannels: UserLoginData is null");
        }
    }

}
