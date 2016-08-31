package com.example.bobyk.mvpeshka.presenter.video;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.utils.PathExtractor;
import com.example.bobyk.mvpeshka.view.video.UploadVideoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class UploadVideoPresenter implements IUploadVideoPresenter {

    private String TAG = "upload";

    private Activity mContext;
    private Fragment mFragment;
    private UploadVideoView mView;

    private List<File> mList = new ArrayList<>();
    private List<String> uploadedVideoNames = new ArrayList<>();

    private TransferUtility transferUtility;

    private File file;

    public UploadVideoPresenter(Activity context, Fragment fragment, UploadVideoView view) {
        mContext = context;
        mFragment = fragment;
        mView = view;
        amazonConfig();
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
    public void performFileSearch() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        mFragment.startActivityForResult(intent, Constants.READ_VIDEO_REQUEST_CODE);
    }

    @Override
    public void uploadVideo(int position) {
        final File video = mList.get(position);
        TransferObserver observer = transferUtility.upload(
                Constants.AMAZON_BUCKED,
                video.getName(),
                video
        );

        observer.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (state.equals(TransferState.COMPLETED)) {
                    uploadedVideoNames.add(video.getName());
                    mView.successUploadVideo(uploadedVideoNames);
                    Log.d(TAG, "onStateChanged: uploadVideo " + video.getName());
                    Toast.makeText(mContext, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {

            }

            @Override
            public void onError(int id, Exception ex) {
                mView.error();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data, List<File> list) {
        mList.clear();
        mList.addAll(list);
        if (requestCode == Constants.READ_VIDEO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                Log.d(TAG, "onStateChanged: uri " + uri);
                file = new File(PathExtractor.getPath(mContext, uri));
                mList.add(file);
                mView.updateVideoList(mList);
            }
        } else {
            file = null;
        }
    }

    @Override
    public void deleteUploadVideo(int position) {
        for (String s : uploadedVideoNames) {
        }
        uploadedVideoNames.remove(position);
        mView.successUploadVideo(uploadedVideoNames);
    }

}
