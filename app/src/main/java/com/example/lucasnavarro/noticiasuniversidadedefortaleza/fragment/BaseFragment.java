package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Lucas Navarro on 27/02/2018.
 */

public class BaseFragment extends android.support.v4.app.Fragment {

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventSucess event){}
}
