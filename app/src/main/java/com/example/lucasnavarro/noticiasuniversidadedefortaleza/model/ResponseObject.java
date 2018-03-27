package com.example.lucasnavarro.noticiasuniversidadedefortaleza.model;

import java.io.Serializable;
import java.util.List;

public class ResponseObject<T> implements Serializable {

    private boolean success;

    private String message;

    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
