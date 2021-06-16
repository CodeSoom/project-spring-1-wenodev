package com.weno.auth.errors;

public class UserEmailDuplicatedException extends RuntimeException {
    public UserEmailDuplicatedException(String email) {
        super("User email is already existed: " + email);
    }
}
