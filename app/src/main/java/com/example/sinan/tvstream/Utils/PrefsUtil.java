package com.example.sinan.tvstream.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.sinan.tvstream.AppMenager;

import org.json.JSONObject;

/**
 * Created by Sinan on 16.6.2016.
 */
public class PrefsUtil {
    private static final String TAG ="PrefsUtil";

    public static final String PREFS_LOGIN_USER_DATA_KEY = "__USER_LOGIN__";
    public static final String PREFS_LOGIN_USERNAME_KEY = "USERNAME";
    public static final String PREFS_LOGIN_PASSWORD_KEY = "PASSWORD";

    public static void saveToPrefs(String key, String value){

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AppMenager.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getFromPrefs(String key){
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(AppMenager.getInstance().getApplicationContext());
        try {
            return prefs.getString(key, "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void removeFromPrefs(String key){
        Log.e(TAG, "removeFromPres key:"+key);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AppMenager.getInstance().getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();

    }

}
