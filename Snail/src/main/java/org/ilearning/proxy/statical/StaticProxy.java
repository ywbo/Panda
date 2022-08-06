package org.ilearning.proxy.statical;

import org.ilearning.proxy.dao.IHaloProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态代理类
 *
 * @author yuwenbo
 * @date 2022/8/6 22:16
 **/
public class StaticProxy implements IHaloProxy {
    private Logger logger = LoggerFactory.getLogger(HaloProxy.class);

    private IHaloProxy haloProxy = new HaloProxy();

    @Override
    public void sayHalo() {
        logger.info("Before static proxy invoke.");
        haloProxy.sayHalo();
        logger.info("After static proxy invoke.");

    }
}
