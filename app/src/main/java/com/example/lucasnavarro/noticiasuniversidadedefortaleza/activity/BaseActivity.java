package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventSucess event){}
}
