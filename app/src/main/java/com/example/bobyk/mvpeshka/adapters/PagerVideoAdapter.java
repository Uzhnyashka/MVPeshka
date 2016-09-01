package com.example.bobyk.mvpeshka.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.example.bobyk.mvpeshka.view.video.VideoFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 26.08.16.
 */
public class PagerVideoAdapter extends FragmentStatePagerAdapter {

    SparseArray<Fragment> registerFragments = new SparseArray<>();
    private List<File> mVideos = new ArrayList<>();
    private Context mContext;

    public PagerVideoAdapter(Context context, List<File> videos, FragmentManager fm) {
        super(fm);
        mVideos = videos;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return VideoFragment.newInstance(mVideos.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registerFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registerFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return registerFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mVideos.get(position).getName();
    }

    @Override
    public int getCount() {
        return mVideos.size();
    }
}
