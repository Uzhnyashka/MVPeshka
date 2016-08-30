package com.example.bobyk.mvpeshka.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bobyk.mvpeshka.view.video.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 26.08.16.
 */
public class PagerVideoAdapter extends FragmentPagerAdapter {

    private List<String> mVideosPath = new ArrayList<>();
    private Context mContext;

    public PagerVideoAdapter(Context context, List<String> videosPath, FragmentManager fm) {
        super(fm);
        mVideosPath = videosPath;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        System.out.println("WWW pos: " + position);
        return VideoFragment.newInstance(mVideosPath.get(position), String.valueOf(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }

    @Override
    public int getCount() {
        return mVideosPath.size();
    }
}
