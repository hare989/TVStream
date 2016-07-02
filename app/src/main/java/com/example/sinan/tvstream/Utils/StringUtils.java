package com.example.sinan.tvstream.Utils;

import android.util.Log;

/**
 * Created by Sinan on 9.6.2016.
 */
public class StringUtils {

    public static String timeSubstring(String time){
        String result=null;
        if(!time.isEmpty()) {
            result = new StringBuilder(time.substring(8, 12)).insert(2, ":").toString();
        }
        return result;
    }

    public static String subrstringHttpUrl(String httpsUrl){
        char s = httpsUrl.charAt(4);
        Log.e("StringUtils", "s="+s);
        String urlBezS="";
        if(s=='s') {
            urlBezS = httpsUrl.substring(5);
            Log.e("StringUtils", "Url :"+urlBezS);
        }
        String finalUrl = "http"+urlBezS;
        Log.e("StringUtils", finalUrl);
        return finalUrl;
    }
}
