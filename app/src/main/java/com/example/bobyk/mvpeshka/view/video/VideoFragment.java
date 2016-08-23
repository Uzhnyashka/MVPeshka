package com.example.bobyk.mvpeshka.view.video;

import android.content.res.AssetFileDescriptor;
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

import com.example.bobyk.mvpeshka.R;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;

/**
 * Created by bobyk on 23.08.16.
 */
public class VideoFragment extends Fragment implements TextureView.SurfaceTextureListener{

    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    private File file;

    public static VideoFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString("AndrashHuiSuka", s);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String filePath = getArguments().getString("AndrashHuiSuka");
        file = new File(filePath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //   View view = inflater.inflate(R.layout.loading_fragment_layout, null);
        View view = inflater.inflate(R.layout.video_fragment, null);

        textureView = (TextureView) view.findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(this);

        return view;
    }



    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);

        try {
            //  AssetFileDescriptor afd = getActivity().getAssets().openFd("yoyoyo.mp4");
            mediaPlayer = new MediaPlayer();
            /*mediaPlayer
                    .setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());*/
            FileInputStream fi = new FileInputStream(file);
            FileDescriptor fd = fi.getFD();
            mediaPlayer.setDataSource(fd);

            mediaPlayer.setSurface(surface);
            mediaPlayer.setLooping(true);

            // don't forget to call MediaPlayer.prepareAsync() method when you use constructor for
            // creating MediaPlayer
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
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
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }
}
