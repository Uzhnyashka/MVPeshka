package com.example.bobyk.mvpeshka.presenter.comments;

import com.example.bobyk.mvpeshka.model.Model;
import com.example.bobyk.mvpeshka.model.ModelImpl;
import com.example.bobyk.mvpeshka.model.comments.data.Comment;
import com.example.bobyk.mvpeshka.model.comments.request.CommentRequest;
import com.example.bobyk.mvpeshka.model.comments.response.Comments;
import com.example.bobyk.mvpeshka.view.comments.CommentView;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by bobyk on 19.08.16.
 */
public class CommentPresenter implements ICommentPresenter{

    private Model model = new ModelImpl();

    private CommentView view;
    private Call<Comments> commentCB;
    private Call<ResponseBody> responseCB;

    private final long postID = 1471592071695l;
    private final long userID = 1469792540943l;

    public CommentPresenter(CommentView view) {
        this.view = view;
    }

    @Override
    public void getAllComments() {
        commentCB = model.getCommentList();
        commentCB.enqueue(new Callback<Comments>() {

            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                if (!response.body().getComments().isEmpty()) {
                    List<Comment> list = response.body().getComments();
                    Collections.sort(list, new Comparator<Comment>() {
                        @Override
                        public int compare(Comment lhs, Comment rhs) {
                            if (lhs.getCreated() > rhs.getCreated()) {
                                return 1;
                            } else if (lhs.getCreated() < rhs.getCreated()) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    });
                    view.showCommentList(response.body().getComments());
                } else if (response.body().getComments().isEmpty()) {
                    view.showEmptyList();
                } else {
                     view.showError();
                }
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {

            }
        });
    }

    @Override
    public void deleteComment(long id) {
        responseCB = model.deleteComment(id);
        responseCB.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.successDeletedComment();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError();
            }
        });
    }

    @Override
    public void addComment(String commentText) {
        CommentRequest commentBody = new CommentRequest();
        commentBody.setMessage(commentText);
        commentBody.setPostid(postID);
        commentBody.setUserid(userID);
        commentBody.setVideo(false);
        commentBody.setUrl("test Image");

        responseCB = model.addComment(commentBody);
        responseCB.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                view.successAddComment();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.showError();
            }
        });
    }


    @Override
    public void onStop() {
        if (commentCB != null) {
            commentCB.cancel();
        }
    }
}
