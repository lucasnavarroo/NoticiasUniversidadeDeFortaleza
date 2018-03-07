package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;


import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.RecyclerViewNoticiasAdapter;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.VerNoticiaEvent;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class GeralFragment extends BaseFragment {

    private RecyclerView recyclerViewGeral;
    private RecyclerViewNoticiasAdapter adapter;
    private NoticiaService noticiaService;
    private ImageView imageViewNoticia;
    private CardView cardViewNoticias;
    int ini = 1, fim = 11;

    public GeralFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View geralView = inflater.inflate(R.layout.fragment_geral, container, false);

        //Inicializaçao de variaveis
        noticiaService = new NoticiaService();
        adapter = new RecyclerViewNoticiasAdapter();
        recyclerViewGeral = geralView.findViewById(R.id.recyclerViewGeral);
        imageViewNoticia = geralView.findViewById(R.id.imageViewNoticia);
        cardViewNoticias = geralView.findViewById(R.id.cardViewNoticias);

        //configuraçao do recycleview
        recyclerViewGeral.setHasFixedSize(true);
        recyclerViewGeral.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerViewGeral.setLayoutManager(linearLayoutManager);

        //chamando a requisiçao
        noticiaService.requestNoticias(NoticiaModel.TIPO_GERAL, ini, fim);

//        recyclerViewGeral.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int current_page) {
//                    noticiaService.requestNoticias(NoticiaModel.TIPO_GERAL, ini, fim);
//                    ini+= 11;
//                    fim+=11;
//            }
//        });

        return geralView;
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventSucess event) {
        adapter.refreshNoticias(NoticiaModel.TIPO_GERAL);
        recyclerViewGeral.setAdapter(adapter);
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventFail event) {
        adapter.refreshNoticias(NoticiaModel.TIPO_GERAL);
        recyclerViewGeral.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

}
