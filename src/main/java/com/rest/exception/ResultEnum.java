package com.rest.exception;

public enum ResultEnum {

    FILE_IS_NOT_EXCEL(1004,"文件不是Excel"),
    DATA_IS_NULL(1005,"数据为空，请填写数据"),
    USERNAME_IS_ERROR(1006,"用户名错误"),
    PASSWORD_IS_ERROR(1007,"密码错误");
    Integer code;
    java.lang.String message;
    ResultEnum(Integer code, java.lang.String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public java.lang.String getMessage() {
        return message;
    }

    public void setMessage(java.lang.String message) {
        this.message = message;
    }
}
