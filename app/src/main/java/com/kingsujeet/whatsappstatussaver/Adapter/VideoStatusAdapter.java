package com.kingsujeet.whatsappstatussaver.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kingsujeet.whatsappstatussaver.Activities.ImageDownloadActivity;
import com.kingsujeet.whatsappstatussaver.Activities.VideoDownloadActivity;
import com.kingsujeet.whatsappstatussaver.Fragment.ImageStatusFragment;
import com.kingsujeet.whatsappstatussaver.Fragment.VideoStatusFragment;
import com.kingsujeet.whatsappstatussaver.Model.StatusModel;
import com.kingsujeet.whatsappstatussaver.R;

import java.util.List;

public class VideoStatusAdapter extends  RecyclerView.Adapter<VideoStatusAdapter.VideoViewHolder> {

    private final List<StatusModel> videoModelList;
    Context context;
    VideoStatusFragment videoStatusFragment;

    public VideoStatusAdapter(List<StatusModel> videoModelList, Context context, VideoStatusFragment videoStatusFragment) {
        this.videoModelList = videoModelList;
        this.context = context;
        this.videoStatusFragment = videoStatusFragment;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_video_status,parent,false);
        return new VideoStatusAdapter.VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoViewHolder holder, int position) {


        final StatusModel statusModel = videoModelList.get(position);
        holder.Thumbnail.setImageBitmap(statusModel.getThumbnail());

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        holder.Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.Thumbnail.startAnimation(buttonClick);
                Intent intent = new Intent(context, VideoDownloadActivity.class);
                intent.putExtra("videoPath", statusModel.getPath());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return videoModelList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder{

        ImageView Thumbnail;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);

            Thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }

}
