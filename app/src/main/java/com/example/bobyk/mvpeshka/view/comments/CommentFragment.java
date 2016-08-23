package com.example.bobyk.mvpeshka.view.comments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bobyk.mvpeshka.R;
import com.example.bobyk.mvpeshka.listeners.OnDeleteCommentListener;
import com.example.bobyk.mvpeshka.model.comments.data.Comment;
import com.example.bobyk.mvpeshka.presenter.comments.CommentPresenter;
import com.example.bobyk.mvpeshka.presenter.comments.ICommentPresenter;
import com.example.bobyk.mvpeshka.adapters.RecyclerCommentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 19.08.16.
 */
public class CommentFragment extends Fragment implements CommentView, OnDeleteCommentListener {

    RecyclerView rv;
    Button btnAddComment;
    EditText txtAddComment;

    private List<Comment> mList = new ArrayList<>();

    private ICommentPresenter presenter;
    private RecyclerCommentAdapter adapter;

    public static CommentFragment newInstance() {
        Bundle args = new Bundle();
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_layout, null);
        rv = (RecyclerView) view.findViewById(R.id.rv);
        btnAddComment = (Button) view.findViewById(R.id.add_comment_btn);
        txtAddComment = (EditText) view.findViewById(R.id.add_comment_field);

        adapter = new RecyclerCommentAdapter(mList, getActivity(), this);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(adapter);
        presenter = new CommentPresenter(this);
        presenter.getAllComments();

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtAddComment.getText().toString().equals("")) {
                    presenter.addComment(txtAddComment.getText().toString());
                } else {
                    Toast.makeText(getContext(), "Comment body is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    @Override
    public void showCommentList(List<Comment> list) {
        mList.clear();
        mList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successDeletedComment() {
        Toast.makeText(getContext(), "Comment deleted", Toast.LENGTH_SHORT).show();
        presenter.getAllComments();
    }

    @Override
    public void successAddComment() {
        Toast.makeText(getContext(), "Comment added", Toast.LENGTH_SHORT).show();
        txtAddComment.setText("");
        presenter.getAllComments();
    }

    @Override
    public void onDelete(long id) {
        presenter.deleteComment(id);
    }
}
