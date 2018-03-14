package com.example.lucasnavarro.noticiasuniversidadedefortaleza.adapter.item;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity.NoticiaActivity;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestIdNoticiaEvent;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Lucas Navarro on 06/03/2018.
 */

public class ItemMyViewHolder extends RecyclerView.ViewHolder {
    TextView textViewCardsTitulo;
    TextView textViewCardsData;
    TextView textViewCardsResumo;
    ImageView imageViewCardsNoticias;
    CardView cardViewNoticias;

    public ItemMyViewHolder(View itemView) {
        super(itemView);

        textViewCardsTitulo = itemView.findViewById(R.id.textViewCardsTitulo);
        textViewCardsData = itemView.findViewById(R.id.textViewCardsData);
        textViewCardsResumo = itemView.findViewById(R.id.textViewCardsResumo);
        imageViewCardsNoticias = itemView.findViewById(R.id.imageViewCardsNoticias);
        cardViewNoticias = itemView.findViewById(R.id.cardViewNoticias);
    }

    public void bind(NoticiaModel noticiaModel){
        Context context = imageViewCardsNoticias.getContext();
        Picasso
                .with(context)
//                .load(noticiaModel.getUrlImg())
                .load("https://www.unifor.br/documents/392178/0/campus+%282%29.jpg/f1bb13ba-8f28-08af-8d3e-04ae8dc4c8e1?t=1518204315690")
                .fit()
                .centerCrop()
                .into(imageViewCardsNoticias);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textViewCardsTitulo.setText(Html.fromHtml(noticiaModel.getTitulo(),Html.FROM_HTML_MODE_LEGACY));
            textViewCardsResumo.setText(Html.fromHtml(noticiaModel.getResumo(),Html.FROM_HTML_MODE_LEGACY));
            textViewCardsData.setText(Html.fromHtml(noticiaModel.getDataPublicacao(),Html.FROM_HTML_MODE_LEGACY));
        } else {
            textViewCardsTitulo.setText(Html.fromHtml(noticiaModel.getTitulo()));
            textViewCardsResumo.setText(Html.fromHtml(noticiaModel.getResumo()));
            textViewCardsData.setText(noticiaModel.getDataPublicacao());
        }

    }

}