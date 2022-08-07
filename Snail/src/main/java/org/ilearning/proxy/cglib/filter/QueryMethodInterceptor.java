package org.ilearning.proxy.cglib.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

/**
 * 记录Query开头的方法参数
 *
 * @author yuwenbo
 * @date 2022/8/7 16:41
 **/
public class QueryMethodInterceptor implements MethodInterceptor, ConditionalCallback {
    private Logger logger = LoggerFactory.getLogger(InsertMethodInterceptor.class);

    @Override
    @Nullable
    public Object intercept(Object enhancedConfigInstance, Method beanMethod, Object[] beanMethodArgs, MethodProxy cglibMethodProxy) throws Throwable {
        Object result = cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);
        logger.info("QueryMethodInterceptor" + beanMethod.getName() + "result: " + result);
        return null;
    }

    @Override
    public boolean isMatched(Method method) {
        return method.getName().startsWith("query");
    }
}
