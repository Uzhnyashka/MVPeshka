package com.example.bobyk.mvpeshka.presenter.video;

import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.widget.MediaController;
import android.widget.SeekBar;

import com.example.bobyk.mvpeshka.listeners.OnDeletePlayerListener;
import com.example.bobyk.mvpeshka.view.video.MVideoView;

import java.io.File;

/**
 * Created by bobyk on 29.08.16.
 */
public class VideoPresenter implements IVideoPresenter, MediaController.MediaPlayerControl {

    private String TAG = "QQQ";

    private MVideoView mView;
    public MediaPlayer mp = null;
    private String mFilePath;
    private SeekBar mSeekBar;
    private Runnable runnable;
    private Handler handler;

    private boolean released = true;

    private File file;

    private OnDeletePlayerListener mOnDeletePlayerListener;

    private boolean run = false;

    private boolean prepared = false;

    public VideoPresenter(MVideoView view, String filePath, SeekBar seekBar, OnDeletePlayerListener onDeletePlayerListener) {
        mView = view;
        mFilePath = filePath;
        mSeekBar = seekBar;
        mOnDeletePlayerListener = onDeletePlayerListener;

        file = new File(mFilePath);
        init();
    }

    private void init() {
        handler = new Handler();
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (mp != null && !getReleased()) {
                    if (!mp.isPlaying()) {
                        mp.seekTo(mSeekBar.getProgress());
                        mp.start();
                        mp.pause();
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (mp != null && !getReleased()) {
                    if (mp.isPlaying()) {
                        mp.pause();
                    }
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (mp != null && !getReleased()) {
                    mp.seekTo(mSeekBar.getProgress());
                    mp.start();
                }
            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                handler.postDelayed(this, 100);
            }
        };
        runnable.run();

    }

    private void updateSeekBar() {
        if (mp != null && prepared) {
          //  Log.d(TAG, "updateSeekBar: file " + mFilePath);
            mSeekBar.setProgress(mp.getCurrentPosition());
        }
    }

    @Override
    public synchronized void prepareMediaPlayer(Surface surface, final int cp) {
        try{
            if (mp == null) {
                Log.d(TAG, "new MediaPlayer " + file.getName());
                mp = new MediaPlayer();
                Log.d(TAG, "prepareMediaPlayer: file " + file.getName());
                mp.setDataSource(mFilePath);

                mp.setSurface(surface);
                mp.setLooping(true);

                mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int frameworkError, int extra) {
                        if (frameworkError == MediaPlayer.MEDIA_ERROR_IO) {
                            Log.e(TAG, "TextureVideoView error. File or network related operation errors.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_MALFORMED) {
                            Log.e(TAG, "TextureVideoView error. Bitstream is not conforming to the related coding standard or file spec.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
                            Log.e(TAG, "TextureVideoView error. Media server died. In this case, the application must release the MediaPlayer object and instantiate a new one.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_TIMED_OUT) {
                            Log.e(TAG, "TextureVideoView error. Some operation takes too long to complete, usually more than 3-5 seconds.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
                            Log.e(TAG, "TextureVideoView error. Unspecified media player error.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_UNSUPPORTED) {
                            Log.e(TAG, "TextureVideoView error. Bitstream is conforming to the related coding standard or file spec, but the media framework does not support the feature.");
                        } else if (frameworkError == MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK) {
                            Log.e(TAG, "TextureVideoView error. The video is streamed and its container is not valid for progressive playback i.e the video's index (e.g moov atom) is not at the start of the file.");
                        }
                        return false;
                    }
                });

                mp.prepareAsync();

                mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.d(TAG, "onPrepared: file " + file.getName());
                        prepared = true;
                        released = false;
                        //VideoPresenter.this.start();
                       // VideoPresenter.this.seekTo(cp);
                        mSeekBar.setMax(mp.getDuration());
                    }
                });
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        mView.updateTextureViewSize(width, height);
                    }
                });
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean getReleased() {
        return released;
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
        if (mp != null && !getReleased() && prepared) {
            Log.d(TAG, "stop: file " + file.getName() + " mp.Duration " + mp.getDuration());
            mp.release();
            released = true;
            handler.removeCallbacks(runnable);
            mOnDeletePlayerListener.onDeletePlayer();
        }
      //  mp.release();
    }

    @Override
    public void start() {
        if (mp != null && !mp.isPlaying() && prepared) {
            Log.d(TAG, "start: file " + file.getName() + " mp.Duration " + mp.getDuration());
            mp.start();
        }
    }

    @Override
    public void pause() {
        if (mp != null && mp.isPlaying() && prepared) {
            Log.d(TAG, "pause: file " + file.getName());
            mp.pause();
        }
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
