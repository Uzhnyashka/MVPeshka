package com.example.bobyk.mvpeshka.view.video;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.example.bobyk.mvpeshka.presenter.video.IDownloadVideoPresenter;

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
    //    mUploadedVideoNames = getArguments().getStringArrayList("videoNames");
    //    presenter = new DownloadVideoPresenter(getContext(), mUploadedVideoNames, this);
    //    presenter.downloadVideo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_review_layout, null);
        videoPager = (ViewPager) view.findViewById(R.id.viewPager);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

        init();
        tipaLoad();
        return view;
    }

    private void tipaLoad() {
        progressBar.setVisibility(View.GONE);
        videoPager.setVisibility(View.VISIBLE);
        mVideos.clear();
        Uri uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/VID_20160825_100558.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/VID_20160823_150057.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_164840.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_165158.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/VID_20160823_150057.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_165158.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_164840.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/VID_20160825_100558.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_165158.mp4");
        mVideos.add(getRealPathFromURI(uri));
        uri = Uri.parse("file:///storage/sdcard1/DCIM/Camera/VID_20160326_164840.mp4");
        mVideos.add(getRealPathFromURI(uri));
        adapter.notifyDataSetChanged();
    }

    private void init() {
        adapter = new PagerVideoAdapter(getContext(), mVideos, getChildFragmentManager());
        videoPager.setAdapter(adapter);

        //videoPager.setOffscreenPageLimit(1);
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

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}
