package com.example.bobyk.mvpeshka.view.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.adapters.RecyclerVideoAdapter;
import com.example.bobyk.mvpeshka.listeners.OnDeleteVideoListener;
import com.example.bobyk.mvpeshka.listeners.OnUploadVideoListener;
import com.example.bobyk.mvpeshka.presenter.video.IUploadVideoPresenter;
import com.example.bobyk.mvpeshka.presenter.video.UploadVideoPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class UploadVideoFragment extends Fragment implements UploadVideoView, View.OnClickListener, OnUploadVideoListener, OnDeleteVideoListener {

    private RecyclerView rvVideo;
    private Button btnAddVideo;
    private Button btnPlayVideo;

    private IUploadVideoPresenter presenter;
    private RecyclerVideoAdapter adapter;
    private File f = null;

    private List<File> mList = new ArrayList<>();

    private List<String> mUploadedVideoNames = new ArrayList<>();

    public static UploadVideoFragment newInstance() {
        Bundle args = new Bundle();
        UploadVideoFragment fragment = new UploadVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_video_layout, null);

        rvVideo = (RecyclerView) view.findViewById(R.id.rv_video_list);
        btnAddVideo = (Button) view.findViewById(R.id.btn_add_video);
        btnPlayVideo = (Button) view.findViewById(R.id.btn_video_preview);

        adapter = new RecyclerVideoAdapter(getContext(), mList, this, this);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setAdapter(adapter);

        btnPlayVideo.setOnClickListener(this);
        btnAddVideo.setOnClickListener(this);

        if (presenter == null) {
            presenter = new UploadVideoPresenter(getActivity(), this, this);
        } else {
            System.out.println("PPP YO");
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_video:
                presenter.performFileSearch();
                break;
            case R.id.btn_video_preview:
                showVideoReviewFragment();
                break;
        }
    }

    private void showVideoReviewFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, VideoReviewFragment.newInstance(mUploadedVideoNames));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data, mList);
    }

    @Override
    public void onUpload(int position) {
        presenter.uploadVideo(position);
    }

    @Override
    public void onDelete(int position) {
        presenter.deleteUploadVideo(position);
        mList.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateVideoList(List<File> list) {
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void successUploadVideo(List<String> uploadedVideoNames) {
        mUploadedVideoNames.clear();
        mUploadedVideoNames.addAll(uploadedVideoNames);
        for (String s : mUploadedVideoNames) {
            System.out.println("PPP : successVideoNames" + s);
        }
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "Error Upload", Toast.LENGTH_SHORT).show();
    }
}
