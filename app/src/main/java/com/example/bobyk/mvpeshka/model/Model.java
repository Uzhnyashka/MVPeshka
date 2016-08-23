package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.category.response.Categories;
import com.example.bobyk.mvpeshka.model.comments.request.CommentRequest;
import com.example.bobyk.mvpeshka.model.comments.response.Comments;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * Created by bobyk on 18.08.16.
 */
public interface Model {

    Call<Categories> getCategoryList();

    Call<Comments> getCommentList();

    Call<ResponseBody> deleteComment(long id);

    Call<ResponseBody> addComment(CommentRequest commentBody);
}
