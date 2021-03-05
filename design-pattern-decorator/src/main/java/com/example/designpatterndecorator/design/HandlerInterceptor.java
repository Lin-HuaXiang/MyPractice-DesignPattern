package com.example.designpatterndecorator.design;

public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

    
}
