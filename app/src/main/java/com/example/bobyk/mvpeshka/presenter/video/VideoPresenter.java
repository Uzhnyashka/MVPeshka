package com.example.bobyk.mvpeshka.presenter.video;

import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.view.Surface;
import android.widget.MediaController;

import com.example.bobyk.mvpeshka.view.video.MVideoView;

/**
 * Created by bobyk on 29.08.16.
 */
public class VideoPresenter implements IVideoPresenter, MediaController.MediaPlayerControl {

    private MVideoView mView;
    private MediaPlayer mp = null;
    private String mFilePath;

    public VideoPresenter(MVideoView view, String filePath) {
        mView = view;
        mFilePath = filePath;
    }

    @Override
    public void prepareMediaPlayer(Surface surface) {
        try{
            if (mp != null) {
                System.out.println("WWW wtf!@#");
            }
            mp = new MediaPlayer();
            mp.setDataSource(mFilePath);

            mp.setSurface(surface);
            mp.setLooping(true);
            mp.prepareAsync();


            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                }
            });
            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    mView.updateTextureViewSize(width, height);
                }
            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateTextureViewSize(int width, int height, int widthRoot, int heightRoot) {
        float viewWidth = widthRoot;
        float viewHeight = heightRoot;

        Matrix matrix = new Matrix();

        float h = viewHeight / height;
        float w = viewWidth / width;

        if (h < w) {
            matrix.setScale(h, 1, viewWidth / 2, viewHeight / 2);
        } else {
            matrix.setScale(1, w, viewWidth / 2, viewHeight / 2);
        }

        mView.transformTexture(matrix);
    }

    @Override
    public void start() {
        mp.start();
    }

    @Override
    public void pause() {
        mp.pause();
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        mp.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return mp.isPlaying();
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
