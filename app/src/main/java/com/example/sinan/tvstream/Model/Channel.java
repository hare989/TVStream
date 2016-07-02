package com.example.sinan.tvstream.Model;


import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.InstanceCreator;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Sinan on 19.5.2016.
 */
public class Channel implements Serializable{
    private String TAG = "Channel";
    /*public static class ChannelInstanceCreator implements InstanceCreator<Channel> {
        public Channel createInstance(Type type) {
            return new Channel();
        }
    }*/

    public Channel(){

    }

    public static class ContentAudience implements Serializable
    {
        public ContentAudience(){}

        private int adult;

        public int getAdult() { return this.adult; }

        public void setAdult(int adult) { this.adult = adult; }
    }

    public static class Drm implements Serializable
    {
        public Drm(){}

        @JsonProperty("protected")
        public boolean isProtected;

        public boolean getProtected() { return this.isProtected; }

        public void setProtected(boolean isProtected) { this.isProtected = isProtected; }

        private String playready_la_url;

        public String getPlayreadyLaUrl() { return this.playready_la_url; }

        public void setPlayreadyLaUrl(String playready_la_url) { this.playready_la_url = playready_la_url; }

        private String widevine_la_url;

        public String getWidevineLaUrl() { return this.widevine_la_url; }

        public void setWidevineLaUrl(String widevine_la_url) { this.widevine_la_url = widevine_la_url; }
    }

    public static class Catchup implements Serializable
    {
        public Catchup(){}

        private int enabled;

        public int getEnabled() { return this.enabled; }

        public void setEnabled(int enabled) { this.enabled = enabled; }

        private String duration;

        public String getDuration() { return this.duration; }

        public void setDuration(String duration) { this.duration = duration; }

        private String duration_unit;

        public String getDurationUnit() { return this.duration_unit; }

        public void setDurationUnit(String duration_unit) { this.duration_unit = duration_unit; }
    }


        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private CurrentEPG.Programme currentProgramme;

        public CurrentEPG.Programme getCurrentProgramme() { return currentProgramme; }

        public void setCurrentProgramme(CurrentEPG.Programme currentProgramme) { Log.e("Channel", "in setCurrentProgramme");this.currentProgramme = currentProgramme; }

        private List<EPG.Programme> programmes;

        public List<EPG.Programme> getProgrammes() {
            return this.programmes;
        }

        public void setProgrammes(List<EPG.Programme> programmes) {
        this.programmes = programmes;
    }

        public List<EPG.Programme> getProgrammesByDate(Date date){
            List<EPG.Programme> list = new ArrayList<>();
            List<EPG.Programme> channelProgrammes = getProgrammes();
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
            Date parameterDate=new Date();
            try {
                parameterDate = sdf.parse(date.toString());
            } catch (ParseException e) {
                Log.e(TAG, "getProgrammesByDate ParseException:+"+e.getMessage()+" for date:"+date+" & "+parameterDate);
                e.printStackTrace();
            }
            Log.e(TAG, "parameterDate="+parameterDate);*/

            for(EPG.Programme p : channelProgrammes){

                if(p.getAttributes().getStartDate().compareTo(date)==0){
                    list.add(p);
                }
            }

            return list;
        }

        public List<Date> getProgrammesDateRange()  {
            List<Date> dateRangeList = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
            Date compareHolder = null;
            try {
                compareHolder = sdf.parse("20001111");
            } catch (ParseException e) {
                Log.e(TAG, "Could not parse 20001111");
                e.printStackTrace();
            }

            Log.e(TAG, "getProgrammesDateRange() for Channel "+getName());

            for(EPG.Programme p : getProgrammes()){
                if(p.getAttributes().getStartDate().compareTo(compareHolder)!=0){
                    dateRangeList.add(p.getAttributes().getStartDate());
                    compareHolder=p.getAttributes().getStartDate();
                }
            }
            Log.e(TAG, "in getProgrammesDateRange dateRangeList.size()="+dateRangeList.size());
            return dateRangeList;
        }

        private String external_id;

        public String getExternalId() { return this.external_id; }

        public void setExternalId(String external_id) { this.external_id = external_id; }

        private String epg_channel_id;

        public String getEpgChannelId() { return this.epg_channel_id; }

        public void setEpgChannelId(String epg_channel_id) { this.epg_channel_id = epg_channel_id; }

        private String number;

        public String getNumber() { return this.number; }

        public void setNumber(String number) { this.number = number; }

        private String url;

        public String getUrl() { return this.url; }

        public void setUrl(String url) { this.url = url; }

        private int is_geoblocked;

        public int getIsGeoblocked() { return this.is_geoblocked; }

        public void setIsGeoblocked(int is_geoblocked) { this.is_geoblocked = is_geoblocked; }

        private Integer display_aspect_width;

        public Integer getDisplayAspectWidth() { return this.display_aspect_width; }

        public void setDisplayAspectWidth(Integer display_aspect_width) { this.display_aspect_width = display_aspect_width; }

        private Integer display_aspect_height;

        public Integer getDisplayAspectHeight() { return this.display_aspect_height; }

        public void setDisplayAspectHeight(Integer display_aspect_height) { this.display_aspect_height = display_aspect_height; }

        private String name;

        public String getName() { return this.name; }

        public void setName(String name) { this.name = name; }

        private String description;

        public String getDescription() { return this.description; }

        public void setDescription(String description) { this.description = description; }

        private String img;

        public String getImg() { return this.img; }

        public void setImg(String img) { this.img = img; }

        private String live_img;

        public String getLiveImg() { return this.live_img; }

        public void setLiveImg(String live_img) { this.live_img = live_img; }

        private String rating;

        public String getRating() { return this.rating; }

        public void setRating(String rating) { this.rating = rating; }

        private boolean available;

        public boolean getAvailable() { return this.available; }

        public void setAvailable(boolean available) { this.available = available; }

        private int force_pin;

        public int getForcePin() { return this.force_pin; }

        public void setForcePin(int force_pin) { this.force_pin = force_pin; }

        private boolean audio_only;

        public boolean getAudioOnly() { return this.audio_only; }

        public void setAudioOnly(boolean audio_only) { this.audio_only = audio_only; }

        public ContentAudience content_audience;

        public ContentAudience getContentAudience() { return this.content_audience; }

        public void setContentAudience(ContentAudience content_audience) { this.content_audience = content_audience; }

        private Drm drm;

        public Drm getDrm() { return this.drm; }

        public void setDrm(Drm drm) { this.drm = drm; }

        private Catchup catchup;

        public Catchup getCatchup() { return this.catchup; }

        public void setCatchup(Catchup catchup) { this.catchup = catchup; }

        private String user_rating;

        public String getUserRating() { return this.user_rating; }

        public void setUserRating(String user_rating) { this.user_rating = user_rating; }

        private String share_url;

        public String getShareUrl() { return this.share_url; }

        public void setShareUrl(String share_url) { this.share_url = share_url; }

        private int current_views;

        public int getCurrentViews() { return this.current_views; }

        public void setCurrentViews(int current_views) { this.current_views = current_views; }



}
