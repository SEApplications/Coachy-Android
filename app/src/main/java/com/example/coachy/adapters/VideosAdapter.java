package com.example.coachy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coachy.R;
import com.example.coachy.models.Video;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {

    private Context context;
    private List<Video> mDataSet;
    private String videoUrl;

    public VideosAdapter(Context context, List<Video> mDataSet) {
        this.context = context;
        this.mDataSet = mDataSet;
    }

    //test when video is only with String
    public VideosAdapter(Context context, String videoUrl) {
        this.context = context;
        this.videoUrl = videoUrl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        holder.videoView.setVideoPath(mDataSet.get(position).getVideoUrl());
        holder.videoView.setVideoPath(videoUrl);

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private VideoView videoView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.training_video_view);
        }
    }
}
