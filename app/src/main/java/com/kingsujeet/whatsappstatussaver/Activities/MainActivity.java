package com.kingsujeet.whatsappstatussaver.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;
import com.kingsujeet.whatsappstatussaver.Adapter.SectionPagerAdapter;
import com.kingsujeet.whatsappstatussaver.Fragment.ImageStatusFragment;
import com.kingsujeet.whatsappstatussaver.Fragment.VideoStatusFragment;
import com.kingsujeet.whatsappstatussaver.R;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

       // androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout tabLayout =  findViewById(R.id.tab_layout);
        ViewPager viewPager =  findViewById(R.id.pager);

//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }

        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }

    public void goToSetting(View view) {

        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        findViewById(R.id.setting).startAnimation(buttonClick);

        startActivity(new Intent(getApplicationContext(), SettingActivity.class));

    }

    public void shareApp(View view) {


        final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
        findViewById(R.id.share).startAnimation(buttonClick);

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "I recommend you to download amazing and beautiful WhatsApp Status Saver App" +
                " & Saver your WhatsApp Status " + "\n\n"+ "https://play.google.com/store/apps/details?id=com.learningislife.whatsappstatus.downloader&hl=en";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }
}

