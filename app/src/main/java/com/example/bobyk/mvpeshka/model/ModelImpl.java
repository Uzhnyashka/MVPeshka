package com.example.bobyk.mvpeshka.model;

import com.example.bobyk.mvpeshka.model.api.ApiInterface;
import com.example.bobyk.mvpeshka.model.api.ApiModule;
import com.example.bobyk.mvpeshka.model.category.data.Categories;
import com.example.bobyk.mvpeshka.model.comments.CommentAddBody;
import com.example.bobyk.mvpeshka.model.comments.Comments;

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
    public Call<ResponseBody> addComment(CommentAddBody commentBody) {
        return apiInterface.addCommentRepo(commentBody);
    }


}
