package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.category.data.Categories;
import com.example.bobyk.mvpeshka.model.comments.CommentAddBody;
import com.example.bobyk.mvpeshka.model.comments.Comments;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * Created by bobyk on 18.08.16.
 */
public interface Model {

    Call<Categories> getCategoryList();

    Call<Comments> getCommentList();

    Call<ResponseBody> deleteComment(long id);

    Call<ResponseBody> addComment(CommentAddBody commentBody);
}
