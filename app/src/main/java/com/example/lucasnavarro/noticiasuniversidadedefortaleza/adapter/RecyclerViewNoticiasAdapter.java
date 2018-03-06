package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class RecyclerViewNoticiasAdapter extends RecyclerView.Adapter<RecyclerViewNoticiasAdapter.MyViewHolder> {

    private List<NoticiaModel> noticias;
    private NoticiaService noticiaService;

//    private Context context;
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
                .inflate(R.layout.recycler_view_cards_adapter, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NoticiaModel noticia = noticias.get(position);
        Context context = holder.imageViewCardsNoticias.getContext();

          Picasso
                  .with(context)
                  .load(noticia.getUrlImg())
                  .fit()
                  .centerCrop()
                  .into(holder.imageViewCardsNoticias);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            holder.textViewCardsTitulo.setText(Html.fromHtml(noticia.getTitulo(),Html.FROM_HTML_MODE_LEGACY));
            holder.textViewCardsResumo.setText(Html.fromHtml(noticia.getResumo(),Html.FROM_HTML_MODE_LEGACY));
            holder.textViewCardsData.setText(Html.fromHtml(noticia.getDataPublicacao(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.textViewCardsTitulo.setText(Html.fromHtml(noticia.getTitulo()));
            holder.textViewCardsResumo.setText(Html.fromHtml(noticia.getResumo()));
            holder.textViewCardsData.setText(noticia.getDataPublicacao());
        }
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCardsTitulo;
        TextView textViewCardsData;
        TextView textViewCardsResumo;
        ImageView imageViewCardsNoticias;

        public MyViewHolder(View itemView) {
            super(itemView);

            textViewCardsTitulo = itemView.findViewById(R.id.textViewCardsTitulo);
            textViewCardsData = itemView.findViewById(R.id.textViewCardsData);
            textViewCardsResumo = itemView.findViewById(R.id.textViewCardsResumo);
            imageViewCardsNoticias = itemView.findViewById(R.id.imageViewCardsNoticias);
        }
    }
}
