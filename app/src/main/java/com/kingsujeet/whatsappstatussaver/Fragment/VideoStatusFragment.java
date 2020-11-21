package com.kingsujeet.whatsappstatussaver.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kingsujeet.whatsappstatussaver.Adapter.ImageStatusAdapter;
import com.kingsujeet.whatsappstatussaver.Adapter.VideoStatusAdapter;
import com.kingsujeet.whatsappstatussaver.Model.StatusModel;
import com.kingsujeet.whatsappstatussaver.R;
import com.kingsujeet.whatsappstatussaver.Utils.MyConstants;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class VideoStatusFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    Handler handler = new Handler();

    ArrayList<StatusModel> videoModelList;
    VideoStatusAdapter videoStatusAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_status, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progress);
        videoModelList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        getStatusVideo();

        return view;
    }

    private void getStatusVideo() {

        if (MyConstants.STATUS_DIRECTORY.exists()){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORY.listFiles();

                    if (statusFiles!=null && statusFiles.length>0){

                        Arrays.sort(statusFiles);

                        for (final File stausFile: statusFiles){

                            StatusModel statusModel = new StatusModel(stausFile
                                    , stausFile.getName(), stausFile.getAbsolutePath());

                            statusModel.setThumbnail(getThumbnail(statusModel));

                            if (statusModel.isVideo()){

                                videoModelList.add(statusModel);

                            }



                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                videoStatusAdapter = new VideoStatusAdapter(videoModelList,getContext(),  VideoStatusFragment.this);
                                recyclerView.setAdapter(videoStatusAdapter);
                                videoStatusAdapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);

                            }
                        });

                    }else {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getContext(), "dir not exist",Toast.LENGTH_LONG).show();

                            }
                        });

                    }

                }
            }).start();

        }

    }

    private Bitmap getThumbnail(StatusModel statusModel) {

        if (statusModel.isVideo()){

            return ThumbnailUtils.createVideoThumbnail(String.valueOf(statusModel.getFile().getAbsoluteFile()),
                    MediaStore.Video.Thumbnails.MICRO_KIND);

        }else {

            return  ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
                    MyConstants.THUMBSIZE,
                    MyConstants.THUMBSIZE);

        }

    }
}