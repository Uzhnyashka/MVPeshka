package com.example.bobyk.mvpeshka.view.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.adapters.PagerVideoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class VideoReviewFragment extends Fragment {

    private ViewPager videoPager;
    private List<String> mVideos = new ArrayList<>();
    private PagerVideoAdapter adapter;

    public static VideoReviewFragment newInstance(List<String> list) {
        Bundle args = new Bundle();
        VideoReviewFragment fragment = new VideoReviewFragment();
        fragment.setVideos(list);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_review_fragment_layout, null);
        videoPager = (ViewPager) view.findViewById(R.id.viewPager);

        return view;
    }

    public void setVideos(List<String> videos) {
        mVideos = videos;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new PagerVideoAdapter(getContext(), mVideos, getFragmentManager());
        videoPager.setAdapter(adapter);
    }
}
