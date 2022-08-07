package org.ilearning.proxy.cglib.filter;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * CglibCallbackFilter用来判断方法走哪个Callback
 *
 * @author yuwenbo
 * @date 2022/8/6 23:25
 **/
public class CglibCallbackFilter implements CallbackFilter {
    private final Callback[] callbacks;

    public CglibCallbackFilter(Callback[] callbacks) {
        this.callbacks = callbacks;
        Class<?>[] callbackTypes = new Class<?>[callbacks.length];
        for (int i = 0; i < callbacks.length; i++) {
            callbackTypes[i] = callbacks[i].getClass();
        }
    }

    @Override
    public int accept(Method method) {
        for (int i = 0; i < callbacks.length; i++) {
            Callback callback = callbacks[i];
            if (!(callback instanceof ConditionalCallback) || ((ConditionalCallback) callback).isMatched(method)) {
                return i;
            }
        }
        throw new IllegalStateException("No callback available for method." + method.getName());
    }
}
