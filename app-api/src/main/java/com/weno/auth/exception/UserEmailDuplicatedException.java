package com.weno.auth.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class UserEmailDuplicatedException extends RuntimeException {
    public UserEmailDuplicatedException(String email) {
        super("User email is already existed: " + email);
    }
}
