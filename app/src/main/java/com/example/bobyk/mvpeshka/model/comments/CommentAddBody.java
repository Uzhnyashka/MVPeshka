package com.example.bobyk.mvpeshka.model.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bobyk on 19.08.16.
 */
public class CommentAddBody {
    @SerializedName("userid")
    @Expose
    private long userid;
    @SerializedName("postid")
    @Expose
    private long postid;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     *
     * @return
     * The userid
     */
    public long getUserid() {
        return userid;
    }

    /**
     *
     * @param userid
     * The userid
     */
    public void setUserid(long userid) {
        this.userid = userid;
    }

    /**
     *
     * @return
     * The postid
     */
    public long getPostid() {
        return postid;
    }

    /**
     *
     * @param postid
     * The postid
     */
    public void setPostid(long postid) {
        this.postid = postid;
    }

    /**
     *
     * @return
     * The video
     */
    public Boolean getVideo() {
        return video;
    }

    /**
     *
     * @param video
     * The video
     */
    public void setVideo(Boolean video) {
        this.video = video;
    }

    /**
     *
     * @return
     * The url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     * The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
