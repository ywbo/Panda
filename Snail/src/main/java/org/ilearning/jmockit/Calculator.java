package org.ilearning.jmockit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JMockit 单元测试框架
 *
 * @author yuwenbo
 * @date 2022/8/10 23:09
 **/
public class Calculator {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(Calculator.class);

    /**
     * 非静态函数
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 静态函数
     */
    public static int staticAdd(int a, int b) {
        return a + b;
    }

    /**
     * 空函数
     */
    public void noop() {
        System.out.println("没有被Mock");
    }

    /**
     * 私有函数
     */
    private void privateMethod() {
        System.out.println("private method");
    }

    /**
     * 无参构造函数初始化
     */
    public Calculator() {
        System.out.println("无参构造函数初始化");
    }

    /**
     * 静态代码块初始化
     */
    static {
        System.out.println("静态代码块初始化");
    }
}
