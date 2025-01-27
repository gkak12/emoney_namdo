package com.emoney.comm.exception;

import com.emoney.comm.enums.EmoneyErrorEnums;

public class EmoneyException extends RuntimeException {

    private int statusCode;
    private String message;

    public EmoneyException() {
        this.statusCode = EmoneyErrorEnums.NOT_FOUND.getCode();
        this.message = EmoneyErrorEnums.NOT_FOUND.getMsg();
    }

    public EmoneyException(EmoneyErrorEnums error) {
        this.statusCode = error.getCode();
        this.message = error.getMsg();
    }

    public EmoneyException(EmoneyErrorEnums error, String message) {
        this.statusCode = error.getCode();
        this.message = message;
    }
}
