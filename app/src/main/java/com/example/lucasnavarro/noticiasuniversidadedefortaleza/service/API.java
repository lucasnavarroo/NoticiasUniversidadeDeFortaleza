package com.example.lucasnavarro.noticiasuniversidadedefortaleza.service;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.ResponseObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public interface API {

    //Leitura
    @GET("{tipoNoticia}/")
    Call<ResponseObject<NoticiaModel>> listarNoticias(@Path("tipoNoticia") String tip_notice, @Query("regIni") int ini, @Query("regFim") int fim);
}