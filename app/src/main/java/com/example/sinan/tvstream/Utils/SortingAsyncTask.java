package com.example.sinan.tvstream.Utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.sinan.tvstream.AppMenager;
import com.example.sinan.tvstream.Model.Channel;
import com.example.sinan.tvstream.Model.EPG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sinan on 16.6.2016.
 */
public class SortingAsyncTask extends AsyncTask<Void, Void, Void> {
    AppMenager app;
    public SortingAsyncTaskFinished delegate = null;

    public interface SortingAsyncTaskFinished{
        void onFinished();
    }

    public SortingAsyncTask(SortingAsyncTaskFinished delegate) {
        super();
        app = AppMenager.getInstance();
        this.delegate = delegate;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (app.getEpg() == null) {
            Log.e("SortingAsyncTask", "EPG is null");

        } else if (app.getListOfChannels() == null) {
            Log.e("SortingAsyncTask", "List of channels is null");
        } else {
            Log.e("SortingAsyncTask", "doInBackground");
            String tempId = "";
            Log.e("OkHttp sorting", "start");
            for (EPG.Programme item : app.getEpg().getProgramme()) {
                tempId = item.getAttributes().getChannel();
                for (Channel channel : app.getListOfChannels()) {

                    if (channel.getId().equals(tempId)) {
                        if (channel.getProgrammes() == null) {
                            channel.setProgrammes(new ArrayList<EPG.Programme>());
                        }
                        channel.getProgrammes().add(item);
                        break;
                    }

                }

                /*Log.e("SortingAsyncTask", item.toString());
                if(item.getAttributes()!=null){

                }else{
                    Log.e("item.getAttributes","isNull");
                }
                if(item.getAttributes().getChannel()!=null){
                    Log.e("getChannel=",item.getAttributes().getChannel());
                }
                else{
                    Log.e("getChanel","isNull");
                }
                if (!item.getAttributes().getChannel().equals(tempId))
                {
                    tempId = item.getAttributes().getChannel();
                   // tempList = new ArrayList<EPG.Programme>();

                    for (Channel channel : app.getListOfChannels())
                    {
                        if(channel.getProgrammes()==null){
                            List<EPG.Programme> list = new ArrayList<EPG.Programme>();
                            channel.setProgrammes(list);
                        }
                        if (channel.getId() == tempId) {
                            channel.getProgrammes().add(item);
                        }
                    }
                }
                else{

            }*/
                //tempList.add(item);
            }
            if (app.getListOfChannels() == null) {
                List<Channel> list = new ArrayList<Channel>();
                app.setListOfChannels(list);
            } else {
                Log.e("OkHttp listOfChanels", String.valueOf(app.getListOfChannels().size()));
                for (int i = 0; i < 1; i++) {
                    if (app.getListOfChannels().get(i) != null) {
                        for (EPG.Programme prog : app.getListOfChannels().get(i).getProgrammes()) {
                            Log.e("Programm title", String.valueOf(prog.getTitle()));
                        }
                    } else {
                        Log.e("ListOfChannels.get(i)", "is Null");
                    }
                }
            }

        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        app.setSortedEPG(true);
        Log.e("SortingAsyncTask", "onPostExecute");

        delegate.onFinished();

    }

}

