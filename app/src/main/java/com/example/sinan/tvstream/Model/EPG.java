package com.example.sinan.tvstream.Model;

import android.util.Log;

import com.example.sinan.tvstream.Utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Sinan on 23.5.2016.
 */
public class EPG implements Serializable {


    public static class Attributes implements Serializable
    {
        private String source_info_name;

        public String getSourceInfoName() { return this.source_info_name; }

        public void setSourceInfoName(String source_info_name) { this.source_info_name = source_info_name; }

        private String generator_info_name;

        public String getGeneratorInfoName() { return this.generator_info_name; }

        public void setGeneratorInfoName(String generator_info_name) { this.generator_info_name = generator_info_name; }
    }

    public static class Attributes2 implements Serializable
    {
        private String TAG = "EPG.Attributes2 class";

        private String channel;

        public String getChannel() { return this.channel; }

        public void setChannel(String channel) { this.channel = channel; }

        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private String start;

        public String getStart() { return this.start; }

        public void setStart(String start) { this.start = start; }

        public String getStartSubstring(){ return StringUtils.timeSubstring(getStart()); }

        public Date getStartDate(){
            Date startDate=null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
            try {
                startDate = sdf.parse(getStartDateString());
            } catch (ParseException e) {
                Log.e(TAG, "Start Date: exception "+e.getMessage());
                e.printStackTrace();
            }
            return startDate;
        }

        private String getStartDateString (){
            return getStart().substring(0, 8);
        }

        private String stop;

        public String getStop() { return this.stop; }

        public void setStop(String stop) { this.stop = stop; }

        public String getStopSubstring () { return StringUtils.timeSubstring(getStop()); }
    }

    public static class Attributes3 implements Serializable
    {
        private String src;

        public String getSrc() { return this.src; }

        public void setSrc(String src) { this.src = src; }
    }

    public static class Icon implements Serializable
    {   @SerializedName("@attributes")
        private Attributes3 attributes;

        public Attributes3 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes3 attributes) { this.attributes = attributes; }
    }

    public static class StarRating implements Serializable
    {
        private String value;

        public String getValue() { return this.value; }

        public void setValue(String value) { this.value = value; }
    }

    public static class Attributes4 implements Serializable
    {
        private String system;

        public String getSystem() { return this.system; }

        public void setSystem(String system) { this.system = system; }
    }

    public static class Attributes5 implements Serializable
    {
        private String src;

        public String getSrc() { return this.src; }

        public void setSrc(String src) { this.src = src; }
    }

    public static class Icon2 implements Serializable
    {
        @SerializedName("@attributes")
        private Attributes5 attributes;

        public Attributes5 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes5 attributes) { this.attributes = attributes; }
    }

    public static class Rating implements Serializable
    {
        @SerializedName("@attributes")
        private Attributes4 attributes;

        public Attributes4 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes4 attributes) { this.attributes = attributes; }

        private String value;

        public String getValue() { return this.value; }

        public void setValue(String value) { this.value = value; }

        private Icon2 icon;

        public Icon2 getIcon() { return this.icon; }

        public void setIcon(Icon2 icon) { this.icon = icon; }
    }

    public static class Genres implements Serializable
    {
        private String genre;

        public String getGenre() { return this.genre; }

        public void setGenre(String genre) { this.genre = genre; }
    }

    public static class Credits implements Serializable
    {
        private ArrayList<String> director;

        public ArrayList<String> getDirector() { return this.director; }

        public void setDirector(ArrayList<String> director) { this.director = director; }

        private ArrayList<String> actor;

        public ArrayList<String> getActor() { return this.actor; }

        public void setActor(ArrayList<String> actor) { this.actor = actor; }

        private ArrayList<String> writer;

        public ArrayList<String> getWriter() { return this.writer; }

        public void setWriter(ArrayList<String> writer) { this.writer = writer; }
    }

    public static class Programme implements Serializable
    {
        public Programme(){}

        @SerializedName("@attributes")
        private Attributes2 attributes;

        public Attributes2 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes2 attributes) { this.attributes = attributes; }

        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }

        private String sub_title;

        public String getSubTitle() { return this.sub_title; }

        public void setSubTitle(String sub_title) { this.sub_title = sub_title; }

        private String category;

        public String getCategory() { return this.category; }

        public void setCategory(String category) { this.category = category; }

        private String desc;

        public String getDesc() { return this.desc; }

        public void setDesc(String desc) { this.desc = desc; }

        private Icon icon;

        public Icon getIcon() { return this.icon; }

        public void setIcon(Icon icon) { this.icon = icon; }

        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }

        private StarRating star_rating;

        public StarRating getStarRating() { return this.star_rating; }

        public void setStarRating(StarRating star_rating) { this.star_rating = star_rating; }

        private Rating rating;

        public Rating getRating() { return this.rating; }

        public void setRating(Rating rating) { this.rating = rating; }

        private Genres genres;

        public Genres getGenres() { return this.genres; }

        public void setGenres(Genres genres) { this.genres = genres; }

        private Credits credits;

        public Credits getCredits() { return this.credits; }

        public void setCredits(Credits credits) { this.credits = credits; }
    }

        @SerializedName("@attributes")
        private Attributes attributes;

        public Attributes getAttributes() { return this.attributes; }

        public void setAttributes(Attributes attributes) { this.attributes = attributes; }

        private ArrayList<Programme> programme;

        public ArrayList<Programme> getProgramme() { return this.programme; }

        public void setProgramme(ArrayList<Programme> programme) { this.programme = programme; }

}
