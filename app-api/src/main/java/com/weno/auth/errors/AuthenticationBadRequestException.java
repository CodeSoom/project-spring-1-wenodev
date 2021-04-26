package com.weno.auth.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AuthenticationBadRequestException extends RuntimeException{
    public AuthenticationBadRequestException(){
        super("비밀번호가 일치하지 않습니다.");
    }
}
