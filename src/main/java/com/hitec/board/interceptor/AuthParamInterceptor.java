package com.hitec.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AuthParamInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            System.out.println(request.getHeader("X-USERINFO"));
            System.out.println(request.getHeader("X-ID-Token"));
            System.out.println(request.getHeader("X-Access-Token"));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
