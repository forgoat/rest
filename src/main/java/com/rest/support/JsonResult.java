package com.rest.support;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private T data;
    private String message = "";

    public static JsonResult isOk() {
        return new JsonResult().message("Success");
    }

    public static JsonResult isFail() {
        return new JsonResult().message("Fail");
    }

    public JsonResult data(T data) {
        this.setData(data);
        return this;
    }

    public JsonResult message(String messgae) {
        this.setMessage(messgae);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
