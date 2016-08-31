package com.example.bobyk.mvpeshka.presenter.video;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.listeners.OnDownloadVideoListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 30.08.16.
 */

public class DownloadVideoPresenter implements IDownloadVideoPresenter {

    private String TAG = "download";

    private Context mContext;
    private OnDownloadVideoListener mOnDownloadVideoListener;
    private List<String> mUploadedVideoNames = new ArrayList<>();

    private List<String> downloadedVideoPath = new ArrayList<>();

    private TransferUtility transferUtility;

    public DownloadVideoPresenter(Context context, List<String> uploadedVideoNames, OnDownloadVideoListener onDownloadVideoListener) {
        mContext = context;
        mOnDownloadVideoListener = onDownloadVideoListener;
        mUploadedVideoNames = uploadedVideoNames;

        amazonConfig();

        init();
    }

    private void init() {
        downloadedVideoPath.clear();
    }

    private void amazonConfig() {
        CognitoCachingCredentialsProvider credentialProvider = new CognitoCachingCredentialsProvider(
                mContext,
                Constants.AMAZON_AUTH,
                Regions.EU_WEST_1
        );
        AmazonS3 s3 = new AmazonS3Client(credentialProvider);
        transferUtility = new TransferUtility(s3, mContext);
    }

    @Override
    public void downloadVideo() {
        for (int i = 0; i < mUploadedVideoNames.size(); i++) {
            String name = mUploadedVideoNames.get(i);
            final File newFile = new File(mContext.getCacheDir().getAbsolutePath(), name);
            TransferObserver observer = transferUtility.download(
                    Constants.AMAZON_BUCKED,
                    name,
                    newFile
            );

            observer.setTransferListener(new TransferListener() {
                @Override
                public void onStateChanged(int id, TransferState state) {
                    if (state.equals(TransferState.COMPLETED)) {
                        downloadedVideoPath.add(newFile.getPath());
                        Log.d(TAG, "onStateChanged: downloadVideo " + newFile.getName());
                        if (downloadedVideoPath.size() == mUploadedVideoNames.size()) {
                            mOnDownloadVideoListener.onDownloadFinish(downloadedVideoPath);
                        }
                    }
                }

                @Override
                public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

                }

                @Override
                public void onError(int id, Exception ex) {

                }
            });
        }
    }
}
