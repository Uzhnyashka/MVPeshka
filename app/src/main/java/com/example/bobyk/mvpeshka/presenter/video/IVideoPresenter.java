package com.example.bobyk.mvpeshka.presenter.video;

import android.view.Surface;

/**
 * Created by bobyk on 29.08.16.
 */
public interface IVideoPresenter {
    void prepareMediaPlayer(Surface surface, boolean visible);
    void updateTextureViewSize(int width, int height, int widthRoot, int heightRoot);
}
