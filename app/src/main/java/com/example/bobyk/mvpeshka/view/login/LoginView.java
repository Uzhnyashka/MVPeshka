package com.example.bobyk.mvpeshka.view.login;

import android.media.session.MediaSession;

/**
 * Created by bobyk on 18.08.16.
 */
public interface LoginView {
    void onSuccessLogin(String userID, String token);
    void onCancelLogin();
    void onErrorLogin();
}
