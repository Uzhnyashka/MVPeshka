package com.example.bobyk.mvpeshka.model.comments.response;

import com.example.bobyk.mvpeshka.model.comments.data.Comment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bobyk on 19.08.16.
 */
public class Comments {
    @SerializedName("comments")
    @Expose
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     *
     * @return
     * The comments
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     *
     * @param comments
     * The comments
     */
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
