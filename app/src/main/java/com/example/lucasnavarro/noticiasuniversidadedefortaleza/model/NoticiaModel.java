package com.example.lucasnavarro.noticiasuniversidadedefortaleza.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Lucas Navarro on 22/02/2018.
 */

public class NoticiaModel extends RealmObject {

    public static final String TIPO_GERAL = "noticia";
    public static final String TIPO_ESPORTES = "esportivo";
    public static final String TIPO_EVENTOS = "evento";

    @PrimaryKey
    private Integer id;
    private String dataPublicacao;
    private String urlImg;
    private String titulo;
    private String corpo;
    private String resumo;
    private String tipo;

    public NoticiaModel(Integer id, String dataPublicacao, String urlImg, String titulo, String corpo, String resumo, String tipo) {
        this.id = id;
        this.dataPublicacao = dataPublicacao;
        this.urlImg = urlImg;
        this.titulo = titulo;
        this.corpo = corpo;
        this.resumo = resumo;
        this.tipo = tipo;
    }

    public NoticiaModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
