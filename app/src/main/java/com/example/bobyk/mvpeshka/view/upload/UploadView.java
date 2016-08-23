package com.example.bobyk.mvpeshka.view.upload;

/**
 * Created by bobyk on 22.08.16.
 */
public interface UploadView {
    void progressLoading(int progress);
    void errorLoading();
    void successLoading(String result);
}
