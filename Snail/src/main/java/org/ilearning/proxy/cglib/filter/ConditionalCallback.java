package org.ilearning.proxy.cglib.filter;

import org.springframework.cglib.proxy.Callback;

import java.lang.reflect.Method;

/**
 * 某种条件下调用Callback
 *
 * @author yuwenbo
 * @date 2022/8/7 16:27
 **/
public interface ConditionalCallback extends Callback {
    /**
     * 是否匹配
     *
     * @param method 方法
     * @return boolean
     */
    boolean isMatched(Method method);
}
