package com.example.bobyk.mvpeshka.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.listeners.OnDeleteCommentListener;
import com.example.bobyk.mvpeshka.model.comments.data.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 19.08.16.
 */
public class RecyclerCommentAdapter extends RecyclerView.Adapter<RecyclerCommentAdapter.ViewHolder> {

    private List<Comment> mCommentList = new ArrayList<>();
    private Context mContext;
    private OnDeleteCommentListener mOnDeleteCommentListener;

    public RecyclerCommentAdapter(List<Comment> mCommentList, Context mContext, OnDeleteCommentListener mOnDeleteCommentListener) {
        this.mCommentList = mCommentList;
        this.mContext = mContext;
        this.mOnDeleteCommentListener = mOnDeleteCommentListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Comment comment = mCommentList.get(position);
        holder.tvTime.setText(comment.getPosted());
        holder.tvData.setText(comment.getUser().getUsername() + ": " + comment.getMessage());
        holder.btnDeleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnDeleteCommentListener.onDelete(comment.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCommentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTime;
        private TextView tvData;
        private Button btnDeleteComment;

        public ViewHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tvData);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);
            btnDeleteComment = (Button) itemView.findViewById(R.id.btn_delete_comment);
        }
    }
}
