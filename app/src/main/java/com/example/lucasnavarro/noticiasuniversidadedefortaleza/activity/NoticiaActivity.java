package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.service.NoticiaService;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EBean
@EActivity(R.layout.activity_noticia)
public class NoticiaActivity extends BaseActivity {

    @Bean
    protected NoticiaService noticiaService;

    @Extra
    protected int idNoticia;

    @ViewById
    protected TextView textViewTitulo;

    @ViewById
    protected ImageView imageViewNoticia;

    @ViewById
    protected TextView textViewTextoNoticia;

    @AfterViews
    protected void afterViews() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        NoticiaModel noticia = noticiaService.getNoticia(idNoticia);
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
