package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;

import java.util.List;

public class RecyclerViewNoticiasAdapter extends RecyclerView.Adapter<RecyclerViewNoticiasAdapter.MyViewHolder> {

    private List<NoticiaModel> noticias;
    private NoticiaService noticiaService;

    public RecyclerViewNoticiasAdapter() {
        noticiaService = new NoticiaService();
    }

    public void refreshNoticias(String tipo){
        this.noticias = noticiaService.getListNoticias(tipo);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_noticias_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NoticiaModel noticia = noticias.get(position);
        holder.textViewTitulo.setText(noticia.getTitulo());
//        holder.imageViewNoticia.setImageURI(noticia.getUrlImg());
        holder.textViewTextoNoticia.setText(noticia.getCorpo());
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitulo;
        TextView textViewTextoNoticia;
        ImageView imageViewNoticia;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewTitulo = itemView.findViewById(R.id.textViewTitulo);
            textViewTextoNoticia = itemView.findViewById(R.id.textViewTextoNoticia);
            imageViewNoticia = itemView.findViewById(R.id.imageViewNoticia);
        }
    }
}
