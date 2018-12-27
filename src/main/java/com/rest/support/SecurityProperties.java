package com.rest.support;

import org.springframework.boot.context.properties.ConfigurationProperties;

<<<<<<< HEAD
/**
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@ConfigurationProperties(prefix = "rest.support")
=======
@ConfigurationProperties(prefix = "com.rest")
>>>>>>> jwt
public class SecurityProperties {
    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
