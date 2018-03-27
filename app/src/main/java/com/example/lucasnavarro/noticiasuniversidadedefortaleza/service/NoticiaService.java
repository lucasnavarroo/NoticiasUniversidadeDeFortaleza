package com.example.lucasnavarro.noticiasuniversidadedefortaleza.service;

import android.util.Log;

import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventFail;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.event.RequestNoticiasEventSucess;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.NoticiaModel;
import com.example.lucasnavarro.noticiasuniversidadedefortaleza.model.ResponseObject;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class NoticiaService {

    private static API getAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://services-dev.unifor.br/services-dev/noticias/ultimas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(API.class);
    }

    public void requestNoticias(String typeNotice, int ini, int fim) {

        getAPI().listarNoticias(typeNotice, ini, fim).enqueue(new Callback<ResponseObject<NoticiaModel>>() {
            @Override
            public void onResponse(Call<ResponseObject<NoticiaModel>> call, final Response<ResponseObject<NoticiaModel>> response) {

                if (response.isSuccessful()) {

                    Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(response.body().getData());
                        }
                    }, new Realm.Transaction.OnSuccess() {

                        @Override
                        public void onSuccess() {
                            EventBus.getDefault().post(new RequestNoticiasEventSucess());
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Log.e("Realm", "onError: ", error);
                        }
                    });

                } else {
                    EventBus.getDefault().post(new RequestNoticiasEventFail());
                }
            }

            @Override
            public void onFailure(Call<ResponseObject<NoticiaModel>> call, Throwable t) {
                EventBus.getDefault().post(new RequestNoticiasEventFail());
            }
        });

    }

//    private static void salvarNoticias(List<NoticiaModel> noticias) {
//        Realm realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(noticias);
//        realm.commitTransaction();
//    }

    public List<NoticiaModel> getListNoticias(String tipoNoticia) {
        Realm realm = Realm.getDefaultInstance();

        return realm.where(NoticiaModel.class)
                .equalTo("tipo", tipoNoticia)
                .findAll();

    }

    public NoticiaModel getNoticia(int idNoticia) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(NoticiaModel.class).equalTo("id", idNoticia).findFirst();
    }

}
