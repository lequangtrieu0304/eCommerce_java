package com.ecommerce.ecommerce_java.exceptions;

public class AuthFailException extends IllegalArgumentException{
    public AuthFailException(String msg){
        super(msg);
    }
}
