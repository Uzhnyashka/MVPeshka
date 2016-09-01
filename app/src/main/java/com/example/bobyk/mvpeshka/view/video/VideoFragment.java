package com.example.bobyk.mvpeshka.view.video;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.listeners.OnDeletePlayerListener;
import com.example.bobyk.mvpeshka.presenter.video.VideoPresenter;

import java.io.File;

/**
 * Created by bobyk on 23.08.16.
 */

public class VideoFragment extends Fragment implements MVideoView, TextureView.SurfaceTextureListener, OnDeletePlayerListener {

    private String TAG = "WWW";

    private String filePath;
    private File file;
    private VideoPresenter presenter;

    private TextureView textureView;
    private LinearLayout root;
    private SeekBar seekBar;

    private Surface surface = null;

    private Button btnPause;
    private Button btnStart;

    private boolean ok = false;
    private boolean visible;

    public static VideoFragment newInstance(File file) {
        Bundle args = new Bundle();
        args.putString("filePath", file.getPath());
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filePath = getArguments().getString("filePath");
        file = new File(filePath);
        Log.d(TAG, "onCreate: file " + file.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, null);
        initView(view);
        return view;
    }



    private void initView(View view) {
        textureView = (TextureView) view.findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(this);

        seekBar = (SeekBar) view.findViewById(R.id.seek_bar);
        root = (LinearLayout) view.findViewById(R.id.ll);
        btnPause = (Button) view.findViewById(R.id.btnPause);
        btnStart = (Button) view.findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) {
                    if (ok) {
                        Log.d(TAG, "onClick: ");
        //                presenter.prepareMediaPlayer(surface);

                    }
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter != null) presenter.stop();
            }
        });

        presenter = new VideoPresenter(this, filePath, seekBar, this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visible = isVisibleToUser;
        if (presenter != null) Log.d("WTF", "setUserVisibleHint: visible " + visible + " getReleased " + presenter.getReleased());
        if (visible && presenter != null && presenter.getReleased()) {
            Log.d(TAG, "setUserVisibleHint: YO");
            presenter = null;
            presenter = new VideoPresenter(this, filePath, seekBar, this);
            presenter.prepareMediaPlayer(surface);
           // presenter.start();
        }
        if (!visible && presenter != null) {
            presenter.stop();
        }
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        if (surface == null) {
            Log.d(TAG, "onSurfaceTextureAvailable: create Surface");
            surface = new Surface(surfaceTexture);
            ok = true;
            Log.d(TAG, "onSurfaceTextureAvailable: prepareMediaPlayer " + file.getName());
            presenter.prepareMediaPlayer(surface);
            presenter.stop();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: file " + file.getName());
        presenter.stop();
        ok = false;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter = null;
        Log.d(TAG, "onDestroy: file " + file.getName());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Log.d(TAG, "onSaveInstanceState: current position " + presenter.getCurrentPosition());
        // outState.putInt("cp", presenter.getCurrentPosition());
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }

    @Override
    public void updateTextureViewSize(int width, int height) {
        presenter.updateTextureViewSize(width, height, root.getWidth(), root.getHeight());
    }

    @Override
    public void transformTexture(Matrix matrix) {
        textureView.setTransform(matrix);
    }


    @Override
    public void onDeletePlayer() {

    }
}