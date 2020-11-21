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
import com.kingsujeet.whatsappstatussaver.Fragment.ImageStatusFragment;
import com.kingsujeet.whatsappstatussaver.Model.StatusModel;
import com.kingsujeet.whatsappstatussaver.R;

import java.util.List;

public class ImageStatusAdapter extends RecyclerView.Adapter<ImageStatusAdapter.ImageViewHolder> {

    private final List<StatusModel> imageList;
    Context context;
    ImageStatusFragment imageStatusFragment;

    public ImageStatusAdapter(List<StatusModel> imageList, Context context, ImageStatusFragment imageStatusFragment) {
        this.imageList = imageList;
        this.context = context;
        this.imageStatusFragment = imageStatusFragment;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.item_image_staus,parent,false);
       return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImageViewHolder holder, int position) {

        final StatusModel statusModel = imageList.get(position);
        holder.Thumbnail.setImageBitmap(statusModel.getThumbnail());

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

        holder.Thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.Thumbnail.startAnimation(buttonClick);
                Intent intent = new Intent(context, ImageDownloadActivity.class);
                intent.putExtra("BitmapImage", statusModel.getPath());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView Thumbnail;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            Thumbnail = itemView.findViewById(R.id.thumbnail);

        }
    }
}
