package org.ilearning.proxy.jdk.statical;

import org.ilearning.proxy.jdk.dao.IHaloProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建静态代理类
 *
 * @author yuwenbo
 * @date 2022/8/6 22:17
 **/
public class HaloProxy implements IHaloProxy {
    private Logger logger = LoggerFactory.getLogger(HaloProxy.class);

    @Override
    public void sayHalo() {
        logger.info("我是静态代理的被代理类......");
    }
}
