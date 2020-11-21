package com.kingsujeet.whatsappstatussaver.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.kingsujeet.whatsappstatussaver.R;
import com.potyvideo.library.AndExoPlayerView;

import java.io.File;
import java.util.UUID;

public class VideoDownloadActivity extends AppCompatActivity {

    private AdView mAdView;
    InterstitialAd interstitialAd = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_download);

        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest1 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest1);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        AndExoPlayerView andExoPlayerView = findViewById(R.id.andExoPlayerView);
        Intent intent = getIntent();
        String videoUri = intent.getExtras().getString("videoPath");

        andExoPlayerView.setSource(videoUri);

    }

    public void shareVideoToWhatsapp(View view) {

        Intent intent = getIntent();
        Uri videoUri = Uri.parse(intent.getExtras().getString("videoPath"));

        Uri uri = videoUri;
        Intent videoshare = new Intent(Intent.ACTION_SEND);
        videoshare.setType("*/*");
        videoshare.setPackage("com.whatsapp");
        videoshare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        videoshare.putExtra(Intent.EXTRA_STREAM,uri);

        startActivity(videoshare);

    }

    public void saveToGallery(View view) {

        Intent intent = getIntent();
        String videoUri = intent.getStringExtra("videoPath");

        addVideo(videoUri);

    }

    public Uri addVideo(String videoPath) {
        String uniqueString = UUID.randomUUID().toString();
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.Video.Media.TITLE, uniqueString);
        values.put(MediaStore.Video.Media.MIME_TYPE, "video/mp4");
        values.put(MediaStore.Video.Media.DATA, videoPath);
        Toast.makeText(getApplicationContext(),"Status Saved Successfully!",Toast.LENGTH_LONG).show();

        return getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
    }

    public void goBack(View view) {

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        findViewById(R.id.back).startAnimation(buttonClick);
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            finish();
        }
    }

    @Override
    public void onBackPressed() {

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
            interstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }

    }

}