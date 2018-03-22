package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.BaseEvent;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.NoticiasFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class PageAdapter extends FragmentPagerAdapter {

    private NoticiasFragment geralFragment;
    private NoticiasFragment eventosFragment;
    private NoticiasFragment esportesFragment;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (geralFragment == null) {
                    geralFragment = new NoticiasFragment();
                    geralFragment.setTipoNoticia(NoticiaModel.TIPO_GERAL);
                }

                return geralFragment;
            case 1:
                if (eventosFragment == null) {
                    eventosFragment = new NoticiasFragment();
                    eventosFragment.setTipoNoticia(NoticiaModel.TIPO_EVENTOS);
                }

                return eventosFragment;
            case 2:
                if (esportesFragment == null) {
                    esportesFragment = new NoticiasFragment();
                    esportesFragment.setTipoNoticia(NoticiaModel.TIPO_ESPORTES);
                }

                return esportesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "GERAL";
            case 1:
                return "EVENTOS";
            case 2:
                return "ESPORTES";
            default:
                return null;
        }
    }
}
