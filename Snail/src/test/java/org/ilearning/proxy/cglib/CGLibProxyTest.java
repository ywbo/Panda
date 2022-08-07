package org.ilearning.proxy.cglib;

import org.junit.jupiter.api.Test;

/**
 * CGLib测试类
 *
 * @author yuwenbo
 * @date 2022/8/6 23:19
 **/
public class CGLibProxyTest {
    @Test
    public void test_CglibProxy() {
        CglibProxy cglibProxy = new CglibProxy();
        cglibProxy.haloCglibProxy("Halo");
    }
}
