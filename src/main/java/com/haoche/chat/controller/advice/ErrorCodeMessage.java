package com.haoche.chat.controller.advice;


public enum ErrorCodeMessage {

    MethodNotSupported("01","方法method错误"),
    AuthenticationFailed("02","用户校验失败"),
    MultipartFailed("03","文件上传限制"),
    UnknownException("100","未知异常");

    private String code;

    private String msg;

    ErrorCodeMessage(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
