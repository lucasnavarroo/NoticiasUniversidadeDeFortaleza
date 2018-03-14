package com.example.lucasnavarro.noticiasuniversidadedefortaleza.application;

import android.app.Application;

import io.realm.Realm;

public class NoticiaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }

}
