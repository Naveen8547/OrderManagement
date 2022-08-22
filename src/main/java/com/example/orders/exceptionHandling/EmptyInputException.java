package com.example.orders.exceptionHandling;

public class EmptyInputException extends  RuntimeException {

    private static final long UID=1L;
    private String errorCode;
    private String errorMessage;
    private  String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public EmptyInputException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public EmptyInputException() {
    }
}

