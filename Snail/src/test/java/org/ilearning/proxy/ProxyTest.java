package org.ilearning.proxy;

import org.ilearning.proxy.dao.IHaloProxy;
import org.ilearning.proxy.dynamic.DynamicProxy;
import org.ilearning.proxy.dynamic.HaloProxy;
import org.ilearning.proxy.statical.StaticProxy;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * jdk代理测试类
 * </p>
 * 动态代理测试类
 * </p>
 * 静态代理测试类
 *
 * @author yuwenbo
 * @date 2022/8/6 22:23
 **/
public class ProxyTest {
    private Logger logger = LoggerFactory.getLogger(ProxyTest.class);

    /**
     * 静态代理测试类
     */
    @Test
    public void test_StaticProxy() {
        StaticProxy staticProxy = new StaticProxy();
        staticProxy.sayHalo();
    }

    /**
     * 动态代理测试类
     */
    @Test
    public void test_DynamicProxy() {
        HaloProxy haloProxy = new HaloProxy();
        InvocationHandler handler = new DynamicProxy(haloProxy);
        IHaloProxy dao = (IHaloProxy) Proxy.newProxyInstance(handler.getClass().getClassLoader(), haloProxy.getClass().getInterfaces(), handler);
        dao.sayHalo();
    }

}
