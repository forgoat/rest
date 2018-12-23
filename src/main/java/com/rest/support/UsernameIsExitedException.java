package com.rest.support;

import org.springframework.security.core.AuthenticationException;

/**
 * @author JuboYu on 2018/12/12.
 * @version 1.0
 */
public class UsernameIsExitedException extends AuthenticationException {
    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameIsExitedException(String msg) {
        super(msg);
    }
}
