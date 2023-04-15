package com.ecommerce.ecommerce_java.exceptions;

public class AuthException extends IllegalArgumentException{
    public AuthException(String msg){
        super(msg);
    }
}
