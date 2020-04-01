package com.sys.springbootshiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    @Override
    protected boolean onLoginFailure(AuthenticationToken token,
                                     AuthenticationException e, ServletRequest request, ServletResponse response) {

        System.out.println("**************************************");
        return true ;

    }
}
