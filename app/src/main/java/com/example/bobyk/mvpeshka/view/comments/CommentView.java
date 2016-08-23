package com.example.bobyk.mvpeshka.view.comments;

import com.example.bobyk.mvpeshka.model.comments.data.Comment;

import java.util.List;

/**
 * Created by bobyk on 19.08.16.
 */
public interface CommentView {
    void showCommentList(List<Comment> list);
    void showError();
    void showEmptyList();
    void successDeletedComment();
    void successAddComment();
}
