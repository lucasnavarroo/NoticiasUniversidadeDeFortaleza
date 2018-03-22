package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import android.support.v4.app.Fragment;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.BaseEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseFragment extends Fragment {

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
    public void onEvent(BaseEvent event){}

}
