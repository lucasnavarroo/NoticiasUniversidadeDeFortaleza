package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EsportesFragment extends Fragment {

    public EsportesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_esportes, container, false);
    }

}
