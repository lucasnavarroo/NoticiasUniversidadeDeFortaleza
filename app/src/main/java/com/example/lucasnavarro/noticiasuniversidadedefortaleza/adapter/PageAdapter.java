package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.NoticiasFragment;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment.NoticiasFragment_;

import static com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel.TIPO_GERAL;

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
                    geralFragment = NoticiasFragment_.builder().tipoNoticia(TIPO_GERAL).build();
                }
                return geralFragment;

            case 1:
                if (eventosFragment == null) {
                    eventosFragment = NoticiasFragment_.builder().tipoNoticia(TIPO_GERAL).build();
                }
                return eventosFragment;

            case 2:
                if (esportesFragment == null) {
                    esportesFragment = NoticiasFragment_.builder().tipoNoticia(TIPO_GERAL).build();
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
