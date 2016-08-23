package com.example.bobyk.mvpeshka.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.listeners.OnDeleteVideoListener;
import com.example.bobyk.mvpeshka.listeners.OnUploadVideoListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 23.08.16.
 */
public class RecyclerVideoAdapter extends RecyclerView.Adapter<RecyclerVideoAdapter.ViewHolder> {

    private Context mContext;
    private List<File> mVideos = new ArrayList<>();
    private OnUploadVideoListener mOnUploadVideoListener;
    private OnDeleteVideoListener mOnDeleteVideoListener;

    public RecyclerVideoAdapter(Context context, List<File> videos, OnUploadVideoListener onUploadVideoListener, OnDeleteVideoListener onDeleteVideoListener) {
        mContext = context;
        mVideos = videos;
        mOnUploadVideoListener = onUploadVideoListener;
        mOnDeleteVideoListener = onDeleteVideoListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_video_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int p = position;
        final File file = mVideos.get(position);
        holder.tvVideoName.setText(file.getName());
        holder.btnVideoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnUploadVideoListener.onUpload(file);
            }
        });
        holder.btnVideoDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeleteVideoListener.onDelete(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvVideoName;
        private Button btnVideoUpload;
        private Button btnVideoDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            tvVideoName = (TextView) itemView.findViewById(R.id.tv_video_name);
            btnVideoUpload = (Button) itemView.findViewById(R.id.btn_video_upload);
            btnVideoDelete = (Button) itemView.findViewById(R.id.btn_video_delete);
        }
    }
}
