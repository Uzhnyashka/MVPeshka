package com.example.bobyk.mvpeshka.view.video;

import android.graphics.Matrix;

/**
 * Created by bobyk on 29.08.16.
 */
public interface MVideoView {
    void updateTextureViewSize(int width, int height);
    void transformTexture(Matrix matrix);
}
