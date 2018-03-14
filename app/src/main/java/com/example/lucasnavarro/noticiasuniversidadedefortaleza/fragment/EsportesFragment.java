package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsportesFragment extends BaseFragment {

    @Override
    public void setTipoNoticia(String tipoNoticia) {
        tipoNoticia = NoticiaModel.TIPO_ESPORTES;
        super.setTipoNoticia(tipoNoticia);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setTipoNoticia(getTipoNoticia());

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
