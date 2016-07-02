package com.example.sinan.tvstream.Utils;

import android.util.Log;

import com.example.sinan.tvstream.Model.Channel;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Sinan on 23.5.2016.
 */
public class GsonUtil {
    private String TAG = "GsonUtil";

    public static <T> T deserializeFromJson(String jsonStr, Class<T> model){
        Log.e("GsonUtil","deserializing class:"+model );
        Gson gson = new Gson();
        T obj = gson.fromJson(jsonStr, model);
        return obj;
    }

    public static <T> T deserializeFromJson(String jsonStr, Type type){
        Log.e("GsonUtil","deserializing type:"+type );
        Gson gson = new Gson();
        T obj = gson.fromJson(jsonStr, type);
        return obj;
    }
}
