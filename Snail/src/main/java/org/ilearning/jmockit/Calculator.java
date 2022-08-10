package org.ilearning.jmockit;

/**
 * JMockit 单元测试框架
 *
 * @author yuwenbo
 * @date 2022/8/10 23:09
 **/
public class Calculator {
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
}
