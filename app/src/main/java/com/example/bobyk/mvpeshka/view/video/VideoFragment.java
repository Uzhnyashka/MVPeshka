package com.example.bobyk.mvpeshka.view.video;

import android.content.res.AssetFileDescriptor;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.FloatRange;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.bobyk.mvpeshka.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by bobyk on 23.08.16.
 */
public class VideoFragment extends Fragment implements TextureView.SurfaceTextureListener, MediaController.MediaPlayerControl{

    private TextureView textureView;
    private MediaPlayer mediaPlayer;
    String filePath;
    private File file;
    private float mVideoWidth;
    private float mVideoHeight;
    private LinearLayout root;
    private DiscreteSeekBar seekBar;
    private Handler handler = new Handler();
    private Runnable runnable;

    public static VideoFragment newInstance(String s) {
        Bundle args = new Bundle();
        args.putString("filePath", s);
        VideoFragment fragment = new VideoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        filePath = getArguments().getString("filePath");
        System.out.println("WWW filePath: " + filePath);
        file = new File(filePath);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, null);

        calculateVideoSize();
        initView(view);

        runnable.run();
        return view;
    }

    public String getFileName() {
        if (file != null) {
            return file.getName();
        }
        return "Andrash WTF?";
    }

    private void calculateVideoSize() {
        try {
            FileInputStream fi = new FileInputStream(file);
            FileDescriptor fd = fi.getFD();
            MediaMetadataRetriever metadataRetriever = new MediaMetadataRetriever();
            metadataRetriever.setDataSource(fd);

            String height = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
            String width = metadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);

            mVideoHeight = Float.parseFloat(height);
            mVideoWidth = Float.parseFloat(width);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void initView(View view) {
        mediaPlayer = new MediaPlayer();
        textureView = (TextureView) view.findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(this);
        seekBar = (DiscreteSeekBar) view.findViewById(R.id.seekBar);
        seekBar.setIndicatorPopupEnabled(true);
        DiscreteSeekBar.NumericTransformer numericTransformer = new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value;
            }
        };
        seekBar.setNumericTransformer(numericTransformer);
        root = (LinearLayout) view.findViewById(R.id.ll);

        runnable = new Runnable() {
            @Override
            public void run() {
                updateSeekBar();
                handler.postDelayed(this, 100);
            }
        };

        seekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(seekBar.getProgress());
                    mediaPlayer.start();
                    mediaPlayer.pause();
                }
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                mediaPlayer.start();
            }
        });
    }


    private void updateSeekBar() {

        if (mediaPlayer != null) {
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            int val = seekBar.getProgress();
            seekBar.setIndicatorFormatter(String.format("%1$d:%2$d",val/60000, val / 1000));
        }
    }

    private void updateTextureViewSize(int width, int height) {
       // textureView.setLayoutParams(new LinearLayout.LayoutParams(width, height));

        float viewWidth = root.getWidth();
        float viewHeight = root.getHeight();

        Matrix matrix = new Matrix();

        float h = viewHeight / height;
        float w = viewWidth / width;
        if (h < w) {
            matrix.setScale(h, 1, viewWidth / 2, viewHeight / 2);
        } else if (w < h) {
            matrix.setScale(1, w, viewWidth / 2, viewHeight / 2);
        }

        textureView.setTransform(matrix);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
        System.out.println("WWW : onPause() " + filePath);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("WWW : onResume() " + filePath);
        if (mediaPlayer != null) mediaPlayer.start();
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
        Surface surface = new Surface(surfaceTexture);

        try {
            mediaPlayer = new MediaPlayer();
            FileInputStream fi = new FileInputStream(file);
            FileDescriptor fd = fi.getFD();
            mediaPlayer.setDataSource(fd);

            System.out.println("WWW for filePath: " + filePath + " FILENAME : " + file.getName());
            mediaPlayer.setSurface(surface);
            mediaPlayer.setLooping(true);

            // don't forget to call MediaPlayer.prepareAsync() method when you use constructor for
            // creating MediaPlayer
            mediaPlayer.prepareAsync();
            seekBar.setMin(0);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    System.out.println("WWW duration: " + mediaPlayer.getDuration());
                    seekBar.setMax(mediaPlayer.getDuration());
                }
            });
            mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                @Override
                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                    updateTextureViewSize(width, height);
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
        System.out.println("WWW onDestroy()");
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer = null;
        }
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getAudioSessionId();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return false;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }
}
