package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.api.ApiInterface;
import com.example.bobyk.mvpeshka.model.api.ApiModule;
import com.example.bobyk.mvpeshka.model.category.response.Categories;
import com.example.bobyk.mvpeshka.model.comments.request.CommentRequest;
import com.example.bobyk.mvpeshka.model.comments.response.Comments;

import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * Created by bobyk on 18.08.16.
 */
public class ModelImpl implements Model {

    ApiInterface apiInterface = ApiModule.getApiInterface();

    @Override
    public Call<Categories> getCategoryList() {
        return apiInterface.getRepositories();
    }

    @Override
    public Call<Comments> getCommentList() {
        return apiInterface.getCommentsRepo();
    }

    @Override
    public Call<ResponseBody> deleteComment(long id) {
        return apiInterface.deleteCommentRepo(id);
    }

    @Override
    public Call<ResponseBody> addComment(CommentRequest commentBody) {
        return apiInterface.addCommentRepo(commentBody);
    }
}
