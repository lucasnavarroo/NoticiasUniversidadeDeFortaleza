package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.item.ItemMyViewHolder;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;

import java.util.List;

public class RecyclerViewNoticiasAdapter extends RecyclerView.Adapter<ItemMyViewHolder> {

    private List<NoticiaModel> noticias;

//    private Context context;
    public RecyclerViewNoticiasAdapter(String tipo) {
        refreshNoticias(tipo);
    }

    @Override
    public ItemMyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_viewholder, parent, false);

        return new ItemMyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(ItemMyViewHolder holder, int position) {
        holder.bind(noticias.get(position));
    }

    public void refreshNoticias(String tipo){
        this.noticias = NoticiaService.getListNoticias(tipo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    @Override
    public long getItemId(int position) {
       return noticias.get(position).getId();
    }

}
