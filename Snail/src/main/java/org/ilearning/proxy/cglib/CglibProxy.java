package org.ilearning.proxy.cglib;

import org.ilearning.proxy.cglib.filter.CglibCallbackFilter;
import org.ilearning.proxy.cglib.filter.InsertMethodInterceptor;
import org.ilearning.proxy.cglib.filter.QueryMethodInterceptor;
import org.ilearning.proxy.cglib.sevice.UserService;
import org.ilearning.proxy.cglib.sevice.impl.UserServiceImpl;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

/**
 * CGLib 代理类的使用
 *
 * @author yuwenbo
 * @date 2022/8/7 16:47
 **/
public class CglibProxy {
    public void haloCglibProxy(String name) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        Callback[] callback = new Callback[]{
                new InsertMethodInterceptor(),
                new QueryMethodInterceptor(),
                NoOp.INSTANCE
        };
        CglibCallbackFilter filter = new CglibCallbackFilter(callback);
        enhancer.setCallbackFilter(filter);
        enhancer.setCallbacks(callback);

        UserService userService = (UserService) enhancer.create();
        userService.query(name);
        userService.insert(name);
    }
}
