package com.leesoft.admin.shared;

import java.io.Serializable;

public class ResponseObject<T> implements Serializable {

    private boolean success;
    private String message;
    private T data;

    public ResponseObject() {
        this.setSuccess(true);
        this.setMessage("Success");
    }

    public ResponseObject(T data) {
        this();
        this.data = data;
    }

    public ResponseObject(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(boolean success, String message) {
        this(success, message, null);
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

    public void setMessage(String format, String... params) {
        if(params != null && params.length > 0){
            this.message = String.format(format, (Object[]) params);
        } else {
            this.message = format;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResponseObject setFailMessage(String format, String... params) {
        ResponseObject response = new ResponseObject();
        response.setSuccess(false);
        response.setFailMessage(format, params);
        return response;
    }
}
