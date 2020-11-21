package com.kingsujeet.whatsappstatussaver.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kingsujeet.whatsappstatussaver.Fragment.ImageStatusFragment;
import com.kingsujeet.whatsappstatussaver.Fragment.VideoStatusFragment;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ImageStatusFragment();
            case 1:
            default:
                return new VideoStatusFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Image Status";
            case 1:
            default:
                return "Video Status";
        }
    }
}
