package com.example.lucasnavarro.noticiasuniversidadedefortaleza.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity.NoticiaActivity;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.RecyclerViewNoticiasAdapter;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.rohit.recycleritemclicksupport.RecyclerItemClickSupport;

import org.greenrobot.eventbus.Subscribe;

import static com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity.NoticiaActivity.EXTRA_ID_NOTICIA;

public class NoticiasFragment extends BaseFragment {
    private static final String TIPO_NOTICIA = "tipoNoticia";
    private static final int PAGE_SIZE = 10;

    private RecyclerView recyclerView;
    private RecyclerViewNoticiasAdapter adapter = new RecyclerViewNoticiasAdapter();
    private String tipoNoticia;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_noticias, container, false);

        if (savedInstanceState != null) {
            tipoNoticia = savedInstanceState.getString(TIPO_NOTICIA);
        }

        recyclerView = view.findViewById(R.id.recyclerViewBase);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        setupRecyclerView();

        return view;
    }

    private void refreshLista() {
        NoticiaService.requestNoticias(tipoNoticia, 1, PAGE_SIZE + 1);
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                if (totalItemCount - 1 == lastVisibleItem) {
                    Log.d("**", "onScrolled() -> " + totalItemCount + " - 1 == " + lastVisibleItem + "" + (totalItemCount - 1 == lastVisibleItem));
                    NoticiaService.requestNoticias(tipoNoticia, totalItemCount, totalItemCount + PAGE_SIZE);
                }

            }
        });

        RecyclerItemClickSupport.addTo(recyclerView).setOnItemClickListener(new RecyclerItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                int idNoticia = (int) adapter.getItemId(position);

                Intent intent = new Intent(getContext(), NoticiaActivity.class);
                intent.putExtra(EXTRA_ID_NOTICIA, idNoticia);
                startActivity(intent);
            }
        });
    }

    public void setTipoNoticia(String tipoNoticia) {
        this.tipoNoticia = tipoNoticia;
        bind(tipoNoticia);
    }

    private void bind(String tipoNoticia) {
        adapter.refreshNoticias(tipoNoticia);
        NoticiaService.requestNoticias(tipoNoticia, 1, PAGE_SIZE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(TIPO_NOTICIA, tipoNoticia);
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

        if(swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Subscribe
    public void onEvent(RequestNoticiasEventFail event) {
        if(swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        alertaErroConexao();
    }

    public void alertaErroConexao(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Erro de conexão.");
        builder.setMessage("Por favor, verifique sua conexão com a internet.");
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
