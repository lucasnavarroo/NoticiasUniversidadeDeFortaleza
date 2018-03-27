package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity.NoticiaActivity_;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.RecyclerViewNoticiasAdapter;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

@EBean
@EFragment(R.layout.fragment_noticias)
public class NoticiasFragment extends BaseFragment {

    private static final int PAGE_SIZE = 10;

    @Bean
    protected NoticiaService noticiaService;

    @Bean
    protected RecyclerViewNoticiasAdapter adapter;

    @FragmentArg("stringTipoNoticia")
    protected String tipoNoticia;

    @ViewById
    protected RecyclerView recyclerViewBase;

    @ViewById
    protected SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            tipoNoticia = savedInstanceState.getString("tipoNoticia");
        }
        return null;
    }

    @AfterViews
    protected void afterViews() {
        adapter.refreshNoticias(tipoNoticia);
        noticiaService.requestNoticias(tipoNoticia, 1, PAGE_SIZE);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerViewBase.setAdapter(adapter);
        recyclerViewBase.setHasFixedSize(true);
        recyclerViewBase.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerViewBase, int dx, int dy) {

                super.onScrolled(recyclerViewBase, dx, dy);

                int totalItemCount = recyclerViewBase.getLayoutManager().getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) recyclerViewBase.getLayoutManager()).findLastVisibleItemPosition();

                if (totalItemCount - 1 == lastVisibleItem) {
                    noticiaService.requestNoticias(tipoNoticia, totalItemCount, totalItemCount + PAGE_SIZE);
                }
            }
        });

        RecyclerItemClickSupport.addTo(recyclerViewBase).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerViewBase, int position, View v) {
                int idNoticia = (int) adapter.getItemId(position);
                NoticiaActivity_.intent(getContext()).idNoticia(idNoticia).start();
            }
        });
    }

    private void refreshLista() {
        noticiaService.requestNoticias(tipoNoticia, 1, 11);
        adapter.refreshNoticias(tipoNoticia);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tipoNoticia", tipoNoticia);
    }

    @Override
    public void onStart() {
        super.onStart();

        adapter.refreshNoticias(tipoNoticia);
        setupRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshLista();
                    }
                }
        );
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventSucess event) {
        adapter.refreshNoticias(tipoNoticia);
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventFail event) {
        alertaErroConexao();
    }

    private void alertaErroConexao() {
        final AlertDialog.Builder alertaErro = new AlertDialog.Builder(getContext());
        alertaErro.setTitle("Erro de conexão.");
        alertaErro.setMessage("Por favor, verifique sua conexão com a internet.");
        alertaErro.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertaErro.create().show();
    }
}
