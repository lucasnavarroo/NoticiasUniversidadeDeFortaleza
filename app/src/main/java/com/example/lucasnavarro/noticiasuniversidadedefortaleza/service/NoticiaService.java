package com.example.lucasnavarro.noticiasuniversidadedefortaleza.service;

import android.util.Log;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.Dados;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public class NoticiaService {

    private static API api;

    public NoticiaService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services-dev.unifor.br/services-dev/noticias/ultimas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

    }

    public void requestNoticias(String tip_notice, int ini, int fim){

        api.listarNoticias(tip_notice, ini, fim).enqueue(new Callback<Dados>() {
            @Override
            public void onResponse(Call<Dados> call, Response<Dados> response) {

                if(response.isSuccessful()){

                salvarNoticias(response.body());

                EventBus.getDefault().post(new RequestNoticiasEventSucess());
                    Log.i("List_SIZE", getListNoticias(NoticiaModel.TIPO_GERAL).toString()+"");
                }

            }

            @Override
            public void onFailure(Call<Dados> call, Throwable t) {
                EventBus.getDefault().post(new RequestNoticiasEventFail());
            }
        });

    }

    public void salvarNoticias(Dados dados){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(dados);
        realm.commitTransaction();
    }

    public List<NoticiaModel> getListNoticias(String tipoNoticia){
        Realm realm = Realm.getDefaultInstance();

        List<NoticiaModel> copiaLista;

        realm.beginTransaction();
        RealmResults<NoticiaModel> noticiaModelRealmList = realm.where(NoticiaModel.class).findAll();
        copiaLista = realm.copyFromRealm(noticiaModelRealmList);
        realm.commitTransaction();

        return copiaLista;
    }

}
