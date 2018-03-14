package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;

public class GeralFragment extends BaseFragment {

    @Override
    public void setTipoNoticia(String tipoNoticia) {
        tipoNoticia = NoticiaModel.TIPO_GERAL;
        super.setTipoNoticia(tipoNoticia);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setTipoNoticia(getTipoNoticia());

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
