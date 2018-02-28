package com.example.lucasnavarro.noticiasuniversidadedefortaleza.application;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public class NoticiaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

    }

}
