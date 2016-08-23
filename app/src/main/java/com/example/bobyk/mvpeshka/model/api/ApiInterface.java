package com.example.bobyk.mvpeshka.model.api;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.example.bobyk.mvpeshka.global.Constants;
import com.example.bobyk.mvpeshka.model.category.response.Categories;
import com.example.bobyk.mvpeshka.model.comments.request.CommentRequest;
import com.example.bobyk.mvpeshka.model.comments.response.Comments;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by bobyk on 18.08.16.
 */
public interface ApiInterface {

    @GET(Constants.API_CATEGORY)
    Call<Categories> getRepositories();

    @GET(Constants.API_COMMENT)
    Call<Comments> getCommentsRepo();

    @DELETE(Constants.API_DELETE_COMMENT + "{id}")
    Call<ResponseBody> deleteCommentRepo(@Path("id") long id);

    @POST(Constants.API_ADD_COMMENT)
    Call<ResponseBody> addCommentRepo(@Body CommentRequest commentBody);

}
