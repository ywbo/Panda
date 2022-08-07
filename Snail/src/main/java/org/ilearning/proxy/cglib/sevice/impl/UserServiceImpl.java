package org.ilearning.proxy.cglib.sevice.impl;

import org.ilearning.proxy.cglib.sevice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserServiceImpl
 *
 * @author yuwenbo
 * @date 2022/8/7 21:55
 **/
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public void insert(String name) {
        if (null != name) {
            logger.info("成功插入了一条名为：" + name + "的数据。");
        } else {
            logger.error("插入失败。因为name为空。");
        }
    }

    @Override
    public void query(String name) {
        if (null != name) {
            logger.info("成功查询了一条名为：" + name + "的数据。");
        } else {
            logger.error("查询失败。因为name为空。");
        }
    }
}
