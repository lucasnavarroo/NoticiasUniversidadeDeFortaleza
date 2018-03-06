package com.example.lucasnavarro.noticiasuniversidadedefortaleza.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.R;

import org.greenrobot.eventbus.Subscribe;

public class NoticiaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
    }

}
