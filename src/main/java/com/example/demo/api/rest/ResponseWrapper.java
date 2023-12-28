package com.example.demo.api.rest;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseWrapper<T> {
    private Object data = null;
    private Boolean status = true;
    private Object message;
    private Map<String, Object> meta;
    private String code;

    public ResponseWrapper() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage(List<String> messages) {
        this.message = messages;
    }

    public void setMessage(Map<String, String> messages) {
        this.message = messages;
    }

    public void setData(List<T> data) {
        this.data = data.toArray();
    }

    public void setData(T data) {
        this.data = data;
    }
}
