package com.ecommerce.ecommerce_java.exceptions;

public class NotFoundException extends IllegalArgumentException{
    public NotFoundException(String msg){
        super(msg);
    }
}
