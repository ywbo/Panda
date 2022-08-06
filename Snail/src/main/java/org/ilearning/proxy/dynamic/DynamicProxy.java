package org.ilearning.proxy.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 *
 * @author yuwenbo
 * @date 2022/8/6 22:12
 **/
public class DynamicProxy implements InvocationHandler {
    private Logger logger = LoggerFactory.getLogger(DynamicProxy.class);

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("Before dynamic proxy invoke " + method.getName());
        method.invoke(subject, args);
        logger.info("After dynamic proxy invoke " + method.getName());
        return null;
    }
}
