package com.example.sinan.tvstream.Customs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.sinan.tvstream.Model.EPG;
import com.example.sinan.tvstream.R;

import java.util.List;

/**
 * Created by Sinan on 7.6.2016.
 */
public class CustomRecyclerAdapter extends android.support.v7.widget.RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private ImageLoader imageLoader;
    private Context context;
    private List<EPG.Programme> programmesList;

    public CustomRecyclerAdapter(List<EPG.Programme> programmesList, Context context){
        super();
        this.programmesList = programmesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_programmes_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EPG.Programme programme = programmesList.get(position);
        imageLoader = CustomVolleyRequestQueue.getInstance(context).getImageLoader();
        imageLoader.get(programme.getIcon().getAttributes().getSrc(),
                        ImageLoader.getImageListener(holder.imgProgrammeIcon, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));

        holder.imgProgrammeIcon.setImageUrl(programme.getIcon().getAttributes().getSrc(), imageLoader);
        holder.txtProgrammeTitle.setText(programme.getTitle());
    }

    @Override
    public int getItemCount() {
        return this.programmesList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public NetworkImageView imgProgrammeIcon;
        public TextView txtProgrammeTitle;

        public ViewHolder(View itemView){
            super(itemView);
            imgProgrammeIcon = (NetworkImageView) itemView.findViewById(R.id.imgEPGProgramme);
            txtProgrammeTitle = (TextView) itemView.findViewById(R.id.txtProgrammeTitle);
        }

    }
}
