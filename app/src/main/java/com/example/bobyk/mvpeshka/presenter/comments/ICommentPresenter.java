package com.example.bobyk.mvpeshka.presenter.comments;

import com.example.bobyk.mvpeshka.model.comments.CommentAddBody;

/**
 * Created by bobyk on 19.08.16.
 */
public interface ICommentPresenter {
    void getAllComments();
    void deleteComment(long id);
    void addComment(String commentBody);

    void onStop();
}
