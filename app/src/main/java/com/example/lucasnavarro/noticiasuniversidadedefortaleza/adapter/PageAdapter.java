package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.EsportesFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.EventosFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.GeralFragment;

public class PageAdapter extends FragmentPagerAdapter {

    private GeralFragment geralFragment;
    private EventosFragment eventosFragment;
    private EsportesFragment esportesFragment;

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (geralFragment == null) {
                    geralFragment = new GeralFragment();
                }

                return  geralFragment;
            case 1:
                if (eventosFragment == null) {
                    eventosFragment = new EventosFragment();
                }

                return  eventosFragment;
            case 2:
                if (esportesFragment == null) {
                    esportesFragment= new EsportesFragment();
                }

                return  esportesFragment;
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
