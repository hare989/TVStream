package com.example.sinan.tvstream.Model;

import android.util.Log;
import android.util.TimeUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Sinan on 28.5.2016.
 */
public class CurrentEPG {

    public class Attributes implements Serializable
    {   @SerializedName("source-info-name")
        private String source_info_name;

        public String getSourceInfoName() { return this.source_info_name; }

        public void setSourceInfoName(String source_info_name) { this.source_info_name = source_info_name; }
        @SerializedName("generator-info-name")
        private String generator_info_name;

        public String getGeneratorInfoName() { return this.generator_info_name; }

        public void setGeneratorInfoName(String generator_info_name) { this.generator_info_name = generator_info_name; }
    }

    public class Attributes2 implements Serializable
    {
        private String channel;

        public String getChannel() { return this.channel; }

        public void setChannel(String channel) { this.channel = channel; }

        private String id;

        public String getId() { return this.id; }

        public void setId(String id) { this.id = id; }

        private String start;

        public String getStart() { return this.start; }

        public void setStart(String start) { this.start = start; }

        public String getStartSubstring(){ return new StringBuilder(getStart().substring(8,12)).insert(2,":").toString();  }

        private String stop;

        public String getStop() { return this.stop; }

        public void setStop(String stop) { this.stop = stop; }

        public String getStopSubstring(){ return new StringBuilder(getStop().substring(8,12)).insert(2,":").toString();  }

        public int getDuration(){
            int duration = 0;
            DateFormat sdf = new SimpleDateFormat("hh:MM");
            try {
                Date dateStart = sdf.parse(getStartSubstring());
                Date dateStop = sdf.parse(getStopSubstring());
                Log.e("CurrentEPG", "Start date:"+dateStart+"\nStop date:"+dateStop);
            } catch (ParseException e) {
                Log.e("CurrentEPG", "Failed to parse time:"+e.getMessage());
                e.printStackTrace();
            }
            return duration;
        }
    }

    public class Attributes3 implements Serializable
    {
        private String lang;

        public String getLang() { return this.lang; }

        public void setLang(String lang) { this.lang = lang; }
    }

    public class SubTitle implements Serializable
    {   @SerializedName("@attributes")
        private Attributes3 attributes;

        public Attributes3 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes3 attributes) { this.attributes = attributes; }
    }

    public class Attributes4 implements Serializable
    {
        private String src;

        public String getSrc() { return this.src; }

        public void setSrc(String src) { this.src = src; }
    }

    public class Icon implements Serializable
    {   @SerializedName("@attributes")
        private Attributes4 attributes;

        public Attributes4 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes4 attributes) { this.attributes = attributes; }
    }

    public class StarRating implements Serializable
    {
        private Object value;

        public Object getValue() { return this.value; }

        public void setValue(Object value) { this.value = value; }
    }

    public class Attributes5 implements Serializable
    {
        private String system;

        public String getSystem() { return this.system; }

        public void setSystem(String system) { this.system = system; }
    }

    public class Rating implements Serializable
    {
        private Attributes5 attributes;

        public Attributes5 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes5 attributes) { this.attributes = attributes; }

        private String value;

        public String getValue() { return this.value; }

        public void setValue(String value) { this.value = value; }
    }

    public class Genres implements Serializable
    {
        private String genre;

        public String getGenre() { return this.genre; }

        public void setGenre(String genre) { this.genre = genre; }
    }

    public class Credits implements Serializable
    {
        private Object actor;

        public Object getActor() { return this.actor; }

        public void setActor(Object actor) { this.actor = actor; }

        private String directorStr ;

        public String getDirectorStr() {
            return directorStr;
        }

        public void setDirectorStr(String directorStr) {
            this.directorStr = directorStr;
        }

        private Object director;

        public Object getDirector() { return this.director; }

        public void setDirector(Object director) { this.director = director; }

        private Object writer;

        public Object getWriter() { return this.writer; }

        public void setWriter(Object writer) { this.writer = writer; }
    }

    public class Programme implements Serializable
    {   @SerializedName("@attributes")
        private Attributes2 attributes;

        public Attributes2 getAttributes() { return this.attributes; }

        public void setAttributes(Attributes2 attributes) { this.attributes = attributes; }

        private int timeElapsed;

        public int getTimeElapsed (){
            int timeElapsedInt=0;
            String programmeStart = new StringBuilder(getAttributes().getStart().substring(8,12)).insert(2,":").toString() ;
            String programmeStop = new StringBuilder(getAttributes().getStop().substring(8,12)).insert(2,":").toString();

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
            Date currentLocalTime = cal.getTime();
            DateFormat date = new SimpleDateFormat("HH:mm");
// you can get seconds by adding  "...:ss" to it
            date.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
            String localTime = date.format(currentLocalTime);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            try {
                Date start = sdf.parse(programmeStart);
                Date end = sdf.parse(programmeStop);
                Date b = sdf.parse(localTime);
                double razlika = b.getTime() - start.getTime();
                double trajanje = end.getTime() - start.getTime();
                double procenat = razlika/trajanje*100;
                timeElapsedInt = (int) procenat;
            } catch (ParseException e) {
                e.printStackTrace();
            }
                return timeElapsedInt;


        }

        private String title;

        public String getTitle() { return this.title; }

        public void setTitle(String title) { this.title = title; }
        @SerializedName("sub-title")
        private SubTitle sub_title;

        public SubTitle getSubTitle() { return this.sub_title; }

        public void setSubTitle(SubTitle sub_title) { this.sub_title = sub_title; }

        private String category_id;

        public String getCategoryId() { return this.category_id; }

        public void setCategoryId(String category_id) { this.category_id = category_id; }

        private String category;

        public String getCategory() { return this.category; }

        public void setCategory(String category) { this.category = category; }

        private Object desc;

        public Object getDesc() { return this.desc; }

        public void setDesc(Object desc) { this.desc = desc; }

        private Icon icon;

        public Icon getIcon() { return this.icon; }

        public void setIcon(Icon icon) { this.icon = icon; }

        private String date;

        public String getDate() { return this.date; }

        public void setDate(String date) { this.date = date; }
        @SerializedName("star-rating")
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
