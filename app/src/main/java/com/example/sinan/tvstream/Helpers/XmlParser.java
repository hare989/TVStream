package com.example.sinan.tvstream.Helpers;

import android.util.Log;
import android.util.Xml;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;


/**
 * Created by Sinan on 11.5.2016.
 */
public class XmlParser {

    public static HashMap<String, String> parseXmlFromString (String xmlString)  {
        JSONObject jsonObject = null;
        HashMap<String, String> hm = new HashMap<String, String>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(new StringReader(xmlString));
            int eventType = parser.getEventType();
            String name=null;
            while (eventType != XmlPullParser.END_DOCUMENT) {

                if(eventType == XmlPullParser.START_DOCUMENT) {
                    Log.e("Start document", "Start"+parser.getName());
                } else if(eventType == XmlPullParser.END_DOCUMENT) {
                    Log.e("End document", "End"+parser.getName());
                } else if(eventType == XmlPullParser.START_TAG) {
                    Log.e("Start tag", parser.getName()+" "+parser.getText());
                    name=parser.getName();
                    //jsonObject.put("Start tag ",parser.getName());
                } else if(eventType == XmlPullParser.END_TAG) {
                    Log.e("End tag", parser.getName()+" "+parser.getText());
                } else if(eventType == XmlPullParser.TEXT) {
                    Log.e("Text", parser.getText()+" "+parser.getName());
                    hm.put(name, parser.getText());
                    name=null;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }finally {
            return hm;
        }

    }
}
