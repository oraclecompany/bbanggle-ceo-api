package com.oraclecompany.bbanggle.global.error.exception;


public class NotValidTokenException extends BusinessException {

    public NotValidTokenException(ErrorCode errorCode) {
        super(errorCode);
    }

}