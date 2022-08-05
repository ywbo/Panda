package org.ilearning.thread;

/**
 * 创建线程方式：继承Thread类创建线程
 * </p>
 * 步骤：
 * 1.创建一个继承于Thread类的子类
 * 2.重写Thread类的run() --> 将此线程执行的操作声明在run()中
 * 3.创建Thread类的子类的对象
 * 4.通过此对象调用start()执行线程
 * </p>
 * 题目：遍历100以内的所有偶数
 *
 * @author yuwenbo
 * @since 2022-08-04
 */
public class ExtendsThread extends Thread {
    @Override
    public void run() {
        // 重写run方法
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
