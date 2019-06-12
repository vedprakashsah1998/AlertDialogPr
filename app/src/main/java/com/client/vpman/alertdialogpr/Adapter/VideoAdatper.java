package com.client.vpman.alertdialogpr.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.client.vpman.alertdialogpr.Main3Activity;
import com.client.vpman.alertdialogpr.Model.VideoModel;
import com.client.vpman.alertdialogpr.R;

import java.util.ArrayList;

public class VideoAdatper extends RecyclerView.Adapter<VideoAdatper.ViewHolder>
{

    Context context;
    ArrayList<VideoModel> arrayListVideo;
    Activity activity;
    public VideoAdatper(Context context, ArrayList<VideoModel> arrayListVideo,Activity activity)
    {
            this.context=context;
            this.arrayListVideo=arrayListVideo;
            this.activity=activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_video,parent,false);
        return new VideoAdatper.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).load("file://"+arrayListVideo.get(position).getStr_thumb()).into(holder.imageView);

        holder.relativeLayout.setAlpha(0);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Main3Activity.class);
                intent.putExtra("video",arrayListVideo.get(position).getStr_path());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListVideo.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        RelativeLayout relativeLayout;
        public ViewHolder(View itemView)
        {
            super(itemView);

            imageView=itemView.findViewById(R.id.iv_image);
            relativeLayout=itemView.findViewById(R.id.relative);
        }
    }

}
