package com.example.bobyk.mvpeshka.model.login.data;

/**
 * Created by bobyk on 18.08.16.
 */
public class UserLoginRequest {
    private String email;
    private String password;
    private String token;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
