package com.example.bobyk.mvpeshka.model.comments;

/**
 * Created by bobyk on 19.08.16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("postid")
    @Expose
    private long postid;
    @SerializedName("created")
    @Expose
    private long created;
    @SerializedName("userid")
    @Expose
    private long userid;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("posted")
    @Expose
    private String posted;
    @SerializedName("video")
    @Expose
    private Boolean video;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getPostid() {
        return postid;
    }


    public void setPostid(long postid) {
        this.postid = postid;
    }


    public long getCreated() {
        return created;
    }


    public void setCreated(long created) {
        this.created = created;
    }


    public long getUserid() {
        return userid;
    }


    public void setUserid(long userid) {
        this.userid = userid;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public String getPosted() {
        return posted;
    }

    /**
     *
     * @param posted
     * The posted
     */
    public void setPosted(String posted) {
        this.posted = posted;
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

}
