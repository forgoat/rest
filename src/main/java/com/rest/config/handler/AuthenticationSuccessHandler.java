package com.rest.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.support.LoginType;
import com.rest.support.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JuboYu on 2018/12/3.
 * @version 1.0
 */
@Component("AuthenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        if (LoginType.JSON.equals(securityProperties.getLoginType())) {
            //System.out.println(authentication);
            response.getWriter().write(objectMapper.writeValueAsString(authentication.getAuthorities()));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
