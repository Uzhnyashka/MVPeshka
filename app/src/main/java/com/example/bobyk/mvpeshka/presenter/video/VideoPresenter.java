package com.example.bobyk.mvpeshka.presenter.video;

import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.example.bobyk.mvpeshka.view.video.MVideoView;

/**
 * Created by bobyk on 29.08.16.
 */
public class VideoPresenter implements IVideoPresenter, MediaController.MediaPlayerControl {

    private String TAG = "WWW";

    private MVideoView mView;
    private MediaPlayer mp = null;
    private String mFilePath;
    private SeekBar mSeekBar;
    private Runnable runnable;
    private Handler handler;

    private boolean run = false;

    private boolean prepared = false;

    public VideoPresenter(MVideoView view, String filePath, SeekBar seekBar) {
        mView = view;
        mFilePath = filePath;
        mSeekBar = seekBar;
        init();
    }

    private void init() {
        mp = new MediaPlayer();
        handler = new Handler();
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!mp.isPlaying()) {
                    mp.seekTo(mSeekBar.getProgress());
                    mp.start();
                    mp.pause();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mp.isPlaying()) {
                    mp.pause();
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(mSeekBar.getProgress());
                mp.start();
            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                handler.postDelayed(this, 100);
            }
        };

    }

    private void updateSeekBar() {
        if (mp != null && prepared) {
            mSeekBar.setProgress(mp.getCurrentPosition());
        }
    }

    @Override
    public void prepareMediaPlayer(Surface surface, final boolean visible) {
        try{
                Log.d(TAG, "prepareMediaPlayer: ");
                mp.setDataSource(mFilePath);

                mp.setSurface(surface);
                mp.setLooping(true);
                mp.prepareAsync();

                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.d(TAG, "onPrepared: mp.duration " + mp.getDuration());
                        prepared = true;
                        if (visible) {
                            VideoPresenter.this.start();
                        }
                        mSeekBar.setMax(mp.getDuration());
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

    public void stop() {
        if (prepared && mp.isPlaying()) {
            mp.stop();
            mp.reset();
            handler.removeCallbacks(runnable);
        }
        Log.d(TAG, "stop: " + mp.getDuration());
      //  mp.release();
    }

    public void release() {
        Log.d(TAG, "release: mp " + mp.getDuration());
        if (prepared) mp.release();
    }

    @Override
    public void start() {
        Log.d(TAG, "start: mp " + mp.getDuration());
        if (mp != null && !mp.isPlaying() && prepared) mp.start();
        if (!run && prepared && mp != null) {
            run = true;
           // runnable.run();
        }
    }

    @Override
    public void pause() {
        if (mp != null && mp.isPlaying() && prepared) mp.pause();
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
