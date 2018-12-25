package com.rest.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class ImportExcelException extends RuntimeException{

    private Integer code;

    public ImportExcelException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ImportExcelException(Integer code, java.lang.String message){

        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
