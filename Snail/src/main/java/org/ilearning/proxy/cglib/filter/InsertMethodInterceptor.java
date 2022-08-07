package org.ilearning.proxy.cglib.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * 记录Insert开头的方法 参数
 *
 * @author yuwenbo
 * @date 2022/8/7 16:34
 **/
public class InsertMethodInterceptor implements MethodInterceptor, ConditionalCallback {
    private Logger logger = LoggerFactory.getLogger(InsertMethodInterceptor.class);

    @Override
    @Nullable
    public Object intercept(Object enhancedConfigInstance, Method beanMethod, Object[] beanMethodArgs, MethodProxy cglibMethodProxy) throws Throwable {
        logger.info("InsertMethodInterceptor " + beanMethod.getName() + "args:" + beanMethodArgs);
        Object result = cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);
        return null;
    }

    @Override
    public boolean isMatched(Method method) {
        return method.getName().startsWith("insert");
    }
}
