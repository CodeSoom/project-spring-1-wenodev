package com.weno.filters.errors;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String token){
        super("Invalid token :" + token);
    }
}
