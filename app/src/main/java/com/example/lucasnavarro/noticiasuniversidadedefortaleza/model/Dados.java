package com.example.lucasnavarro.noticiasuniversidadedefortaleza.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public class Dados extends RealmObject implements Serializable{

    @PrimaryKey
    private int pk;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private RealmList<NoticiaModel> data;

    public Dados(boolean success, String message, RealmList<NoticiaModel> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Dados() {

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RealmList<NoticiaModel> getData() {
        return data;
    }

    public void setData(RealmList<NoticiaModel> data) {
        this.data = data;
    }
}
