package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity.NoticiaActivity;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.EndlessRecyclerOnScrollListener;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.RecyclerViewNoticiasAdapter;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class BaseFragment extends android.support.v4.app.Fragment {

    private RecyclerView recyclerViewBase;
    private RecyclerViewNoticiasAdapter adapter;
    private String tipoNoticia;
    int ini = 1, fim = 11;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View geralView = inflater.inflate(R.layout.fragment_geral, container, false);

        //Inicializaçao de variaveis
        adapter = new RecyclerViewNoticiasAdapter(getTipoNoticia());
        recyclerViewBase = geralView.findViewById(R.id.recyclerViewBase);

        //configuraçao do recycleview
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerViewBase.setLayoutManager(linearLayoutManager);
        recyclerViewBase.setAdapter(adapter);
        recyclerViewBase.setHasFixedSize(true);
        recyclerViewBase.setNestedScrollingEnabled(false);

        NoticiaService.requestNoticias(tipoNoticia, ini, fim);

        EndlessRecyclerOnScrollListener scrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                ini += 11;
                fim += 11;

                NoticiaService.requestNoticias(tipoNoticia, ini, fim);
            }
        };

        recyclerViewBase.addOnScrollListener(scrollListener);

        recyclerViewBase.addOnScrollListener(scrollListener);

//        recyclerViewBase.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//
//                super.onScrolled(recyclerView, dx, dy);
//
//                totalItemCount = linearLayoutManager.getItemCount();
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
//
//                if (totalItemCount - 1 == lastVisibleItem) {
//                    requestNoticia(totalItemCount, totalItemCount + visibleThreshold );
//                }
//
//            }
//        });

        RecyclerItemClickSupport.addTo(recyclerViewBase).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                int idNoticia = (int) adapter.getItemId(position);

                Intent intent = new Intent(getContext(), NoticiaActivity.class);
                intent.putExtra("idNoticia", idNoticia);
                startActivity(intent);
            }
        });

        return geralView;
    }

    public void setTipoNoticia(String tipoNoticia) {
        this.tipoNoticia = tipoNoticia;
    }

    public String getTipoNoticia() {
        return this.tipoNoticia;
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventSucess event) {
        adapter.refreshNoticias(tipoNoticia);
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventFail event) {
        adapter.refreshNoticias(tipoNoticia);
    }

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

}
