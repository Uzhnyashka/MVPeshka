package com.example.bobyk.mvpeshka.view.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.adapters.PagerVideoAdapter;
import com.example.bobyk.mvpeshka.listeners.OnDownloadVideoListener;
import com.example.bobyk.mvpeshka.presenter.upload.IUploadPresenter;
import com.example.bobyk.mvpeshka.presenter.video.DownloadVideoPresenter;
import com.example.bobyk.mvpeshka.presenter.video.IDownloadVideoPresenter;
import com.example.bobyk.mvpeshka.presenter.video.IUploadVideoPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class VideoReviewFragment extends Fragment implements OnDownloadVideoListener{

    private ViewPager videoPager;
    private ProgressBar progressBar;
    private List<String> mVideos = new ArrayList<>();
    private PagerVideoAdapter adapter;

    private IDownloadVideoPresenter presenter;

    private List<String> mUploadedVideoNames = new ArrayList<>();


    public static VideoReviewFragment newInstance(List<String> uploadedVideoNames) {
        Bundle args = new Bundle();
        args.putStringArrayList("videoNames", (ArrayList<String>) uploadedVideoNames);
        VideoReviewFragment fragment = new VideoReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUploadedVideoNames = getArguments().getStringArrayList("videoNames");
        presenter = new DownloadVideoPresenter(getContext(), mUploadedVideoNames, this);
        presenter.downloadVideo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_review_layout, null);
        videoPager = (ViewPager) view.findViewById(R.id.viewPager);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        init();
        return view;
    }

    private void init() {
        adapter = new PagerVideoAdapter(getContext(), mVideos, getChildFragmentManager());
        videoPager.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        videoPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                System.out.println("WWW selected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onDownloadFinish(List<String> path) {
        Toast.makeText(getContext(), "Downloaded", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        videoPager.setVisibility(View.VISIBLE);
        mVideos.clear();
        mVideos.addAll(path);
        adapter.notifyDataSetChanged();
    }
}
