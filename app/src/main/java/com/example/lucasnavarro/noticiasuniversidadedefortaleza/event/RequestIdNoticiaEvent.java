package com.example.lucasnavarro.noticiasuniversidadedefortaleza.event;

/**
 * Created by Lucas Navarro on 07/03/2018.
 */

public class RequestIdNoticiaEvent {
    private int id;

    public RequestIdNoticiaEvent(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
