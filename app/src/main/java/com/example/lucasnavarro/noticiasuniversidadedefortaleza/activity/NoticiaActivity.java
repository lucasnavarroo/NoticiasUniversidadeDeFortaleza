package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class NoticiaActivity extends BaseActivity {
    private int idNoticia;
    private TextView textViewTitulo;
    private ImageView imageViewNoticia;
    private TextView textViewTextoNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textViewTitulo = findViewById(R.id.textViewTitulo);
        imageViewNoticia = findViewById(R.id.imageViewNoticia);
        textViewTextoNoticia = findViewById(R.id.textViewTextoNoticia);

        Bundle extras = getIntent().getExtras();
        idNoticia = extras.getInt("idNoticia");

        NoticiaModel noticia = NoticiaService.getNoticia(idNoticia);

        ajustar(noticia);
    }

    public void ajustar(NoticiaModel noticia) {
        Picasso
                .with(getApplicationContext())
//                .load(noticiaModel.getUrlImg())
                .load("https://www.unifor.br/documents/20143/554520/1.+Campus+%28Desktop%29.jpg/24826674-6639-7f71-c6fc-a3b5f5a54d1f?t=1516039078295")
                .fit()
                .centerCrop()
                .into(imageViewNoticia);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textViewTitulo.setText(Html.fromHtml(noticia.getTitulo(), Html.FROM_HTML_MODE_LEGACY));
            textViewTextoNoticia.setText(Html.fromHtml(noticia.getCorpo(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textViewTitulo.setText(Html.fromHtml(noticia.getTitulo()));
            textViewTextoNoticia.setText(Html.fromHtml(noticia.getCorpo()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
