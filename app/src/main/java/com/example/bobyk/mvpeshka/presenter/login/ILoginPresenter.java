package com.example.bobyk.mvpeshka.presenter.login;

import android.content.Intent;

/**
 * Created by bobyk on 18.08.16.
 */
public interface ILoginPresenter {
    void onLoginResult(int requestCode, int resultCode, Intent data);
}
