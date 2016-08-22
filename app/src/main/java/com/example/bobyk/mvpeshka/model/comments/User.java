package com.example.bobyk.mvpeshka.model.comments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bobyk on 19.08.16.
 */

public class User {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("created")
    @Expose
    private Object created;
    @SerializedName("updated")
    @Expose
    private Object updated;
    @SerializedName("gender")
    @Expose
    private Boolean gender;
    @SerializedName("age")
    @Expose
    private long age;
    @SerializedName("categories")
    @Expose
    private Object categories;
    @SerializedName("spheras")
    @Expose
    private Object spheras;
    @SerializedName("description")
    @Expose
    private Object description;
    @SerializedName("locationid")
    @Expose
    private long locationid;
    @SerializedName("location")
    @Expose
    private Object location;
    @SerializedName("background")
    @Expose
    private Object background;
    @SerializedName("followers")
    @Expose
    private Object followers;
    @SerializedName("hometown")
    @Expose
    private String hometown;
    @SerializedName("postcounts")
    @Expose
    private long postcounts;
    @SerializedName("followercounts")
    @Expose
    private long followercounts;
    @SerializedName("posts")
    @Expose
    private Object posts;

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The login
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The password
     */
    public Object getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(Object password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The created
     */
    public Object getCreated() {
        return created;
    }

    /**
     *
     * @param created
     * The created
     */
    public void setCreated(Object created) {
        this.created = created;
    }

    /**
     *
     * @return
     * The updated
     */
    public Object getUpdated() {
        return updated;
    }

    /**
     *
     * @param updated
     * The updated
     */
    public void setUpdated(Object updated) {
        this.updated = updated;
    }

    /**
     *
     * @return
     * The gender
     */
    public Boolean getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The gender
     */
    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The age
     */
    public long getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The age
     */
    public void setAge(long age) {
        this.age = age;
    }

    /**
     *
     * @return
     * The categories
     */
    public Object getCategories() {
        return categories;
    }

    /**
     *
     * @param categories
     * The categories
     */
    public void setCategories(Object categories) {
        this.categories = categories;
    }

    /**
     *
     * @return
     * The spheras
     */
    public Object getSpheras() {
        return spheras;
    }

    /**
     *
     * @param spheras
     * The spheras
     */
    public void setSpheras(Object spheras) {
        this.spheras = spheras;
    }

    /**
     *
     * @return
     * The description
     */
    public Object getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(Object description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The locationid
     */
    public long getLocationid() {
        return locationid;
    }

    /**
     *
     * @param locationid
     * The locationid
     */
    public void setLocationid(long locationid) {
        this.locationid = locationid;
    }

    /**
     *
     * @return
     * The location
     */
    public Object getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(Object location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The background
     */
    public Object getBackground() {
        return background;
    }

    /**
     *
     * @param background
     * The background
     */
    public void setBackground(Object background) {
        this.background = background;
    }

    /**
     *
     * @return
     * The followers
     */
    public Object getFollowers() {
        return followers;
    }

    /**
     *
     * @param followers
     * The followers
     */
    public void setFollowers(Object followers) {
        this.followers = followers;
    }

    /**
     *
     * @return
     * The hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     *
     * @param hometown
     * The hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     *
     * @return
     * The postcounts
     */
    public long getPostcounts() {
        return postcounts;
    }

    /**
     *
     * @param postcounts
     * The postcounts
     */
    public void setPostcounts(long postcounts) {
        this.postcounts = postcounts;
    }

    /**
     *
     * @return
     * The followercounts
     */
    public long getFollowercounts() {
        return followercounts;
    }

    /**
     *
     * @param followercounts
     * The followercounts
     */
    public void setFollowercounts(long followercounts) {
        this.followercounts = followercounts;
    }

    /**
     *
     * @return
     * The posts
     */
    public Object getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(Object posts) {
        this.posts = posts;
    }

}
