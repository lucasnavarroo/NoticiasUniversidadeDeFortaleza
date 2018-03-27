package com.example.lucasnavarro.noticiasuniversidadedefortaleza.service;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.ResponseObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    //Leitura
    @GET("{tipoNoticia}/")
    Call<ResponseObject<NoticiaModel>> listarNoticias(@Path("tipoNoticia") String tipo_noticia, @Query("regIni") int ini, @Query("regFim") int fim);

}