package com.haoche.chat.controller.advice;


import java.io.Serializable;

public class Template implements Serializable{
    private static final long serialVersionUID = -3124108880506811761L;

    private String code = CODE_SUCCESS;
    public static final String CODE_FAIL = "-1";
    public static final String CODE_SUCCESS = "0";

    private String message = "";

    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setFailMessage(String message) {
        this.code = CODE_FAIL;
        this.message = message;
    }

    public void setSuccessMessage(String message) {
        this.code = CODE_SUCCESS;
        this.message = message;
    }

    public void setResult(ErrorCodeMessage errorCodeMessage) {
        this.code = errorCodeMessage.getCode();
        this.message = errorCodeMessage.getMsg();
    }

    public void setSuccess(Object data) {
        setSuccessMessage("");
        setData(data);
    }

    public void setSuccess(String message, Object data) {
        setSuccessMessage(message);
        setData(data);
    }
}
