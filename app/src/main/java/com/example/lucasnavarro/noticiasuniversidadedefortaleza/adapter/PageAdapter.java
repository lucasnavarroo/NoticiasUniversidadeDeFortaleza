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

    private View view;

    public PageAdapter(FragmentManager fm, View view) {
        super(fm);
        this.view = view;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new GeralFragment();
            case 1:
                return new EventosFragment();
            case 2:
                return new EsportesFragment();
            default:
                return null;
        }
    }

    public void setView(View view){
        this.view = view;
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
