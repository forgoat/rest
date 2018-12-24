package com.rest.support;

import org.springframework.security.core.AuthenticationException;

public class UsernameIsExitedException extends AuthenticationException {
    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameIsExitedException(String msg) {
        super(msg);
    }
}
