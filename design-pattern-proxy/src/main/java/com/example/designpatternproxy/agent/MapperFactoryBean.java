package com.example.designpatternproxy.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import aj.org.objectweb.asm.Handle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    public MapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Select select = method.getAnnotation(Select.class);
                log.info("SQL:{}", select.value().replace("#{uId}", args[0].toString()));
                return args[0] + "- 沉淀、分享、成⻓长，让⾃自⼰己和他⼈人都能有所收获！";
            }

        };
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperInterface}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
      }

}
