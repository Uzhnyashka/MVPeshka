package com.example.bobyk.mvpeshka.view.video;

import java.io.File;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public interface UploadVideoView {
    void updateVideoList(List<File> list);
    void successUploadVideo(List<String> uploadedVideoNames);
    void error();
}
