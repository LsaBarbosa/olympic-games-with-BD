package com.santanna.olympicgames.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg){
        super(msg);
    }
}
