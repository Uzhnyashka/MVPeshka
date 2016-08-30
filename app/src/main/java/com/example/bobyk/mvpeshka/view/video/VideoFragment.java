package com.example.bobyk.mvpeshka.view.video;

import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.presenter.video.IVideoPresenter;
import com.example.bobyk.mvpeshka.presenter.video.VideoPresenter;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;

/**
 * Created by bobyk on 23.08.16.
 */

public class VideoFragment extends Fragment implements MVideoView, TextureView.SurfaceTextureListener {

    private String filePath;
    private File file;
    private IVideoPresenter presenter;
    private VideoView videoView;
    private String msg;

    private TextureView textureView;
    private DiscreteSeekBar seekBar;
    private LinearLayout root;
    private TextView tvVideo;
    private Button btnYO;
    private boolean ok = false;

    private Surface surface = null;

    public static VideoFragment newInstance(String filePath, String msg) {
        Bundle args = new Bundle();
        args.putString("filePath", filePath);
        args.putString("msg", msg);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filePath = getArguments().getString("filePath");
        msg = getArguments().getString("msg");
        System.out.println("WWW ff " + filePath);

        file = new File(filePath);
        presenter = new VideoPresenter(this, filePath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, null);
        ok = false;
        initView(view);

        return view;
    }

    private void initView(View view) {
        textureView = (TextureView) view.findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(this);

        seekBar = (DiscreteSeekBar) view.findViewById(R.id.seekBar);
        tvVideo = (TextView) view.findViewById(R.id.tvVideo);
        tvVideo.setText(msg);
        btnYO = (Button) view.findViewById(R.id.btnYO);
        btnYO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ok) {
                    presenter.prepareMediaPlayer(surface);
                }
            }
        });
        root = (LinearLayout) view.findViewById(R.id.ll);
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        surface = new Surface(surfaceTexture);
        System.out.println("WWW OP new Surface");
        ok = true;
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

}