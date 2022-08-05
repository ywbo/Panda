
package org.ilearning.thread;

/**
 * 线程执行顺序
 * </p>
 * 问题：给定三个线程 T1, T2, T3，怎么保证三个线程按顺序执行？
 *
 * @author yWX983890
 * @since 2022-08-05
 */

import java.util.concurrent.Callable;

/**
 * 继承 Thread 类创建线程
 */
class OrderThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程A");
    }
}

/**
 * 实现 Runnable接口，创建线程
 */
class OrderThreadB implements Runnable {
    Thread threadA;

    public OrderThreadB() {
    }

    public OrderThreadB(Thread threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
        try {
            threadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程B");
    }
}

class OrderThreadC implements Callable {
    Thread threadB;

    public OrderThreadC(Thread threadB) {
        this.threadB = threadB;
    }

    public OrderThreadC() {

    }

    @Override
    public Object call() throws Exception {
        try {
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程C");
        return null;
    }
}
