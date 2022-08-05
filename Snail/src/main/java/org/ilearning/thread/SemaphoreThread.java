
package org.ilearning.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;

/**
 * 信号量：参照操作系统原理进程同步问题
 * </p>
 * 利用信号量来实现给定三个线程 T1, T2, T3，保证三个线程按顺序执行
 *
 * @author yWX983890
 * @since 2022-08-05
 */
class SemaphoreThread extends Thread {
    Semaphore semaphoreD;

    Semaphore semaphoreE;

    public SemaphoreThread(Semaphore semaphoreD, Semaphore semaphoreE) {
        super();
        this.semaphoreD = semaphoreD;
        this.semaphoreE = semaphoreE;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphoreD.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程D");
            // semaphoreE.release();
        }
    }
}

class SemaphoreThreadE implements Runnable {
    Semaphore semaphoreE;

    Semaphore semaphoreF;

    public SemaphoreThreadE(Semaphore semaphoreE, Semaphore semaphoreF) {
        super();
        this.semaphoreE = semaphoreE;
        this.semaphoreF = semaphoreF;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaphoreE.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程E");
            // semaphoreF.release();
        }

    }
}

class SemaphoreThreadF implements Callable {
    Semaphore semaphoreD;

    Semaphore semaphoreF;

    public SemaphoreThreadF(Semaphore semaphoreD, Semaphore semaphoreF) {
        super();
        this.semaphoreD = semaphoreD;
        this.semaphoreF = semaphoreF;
    }

    @Override
    public Object call() throws Exception {
        while (true) {
            try {
                semaphoreF.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程F");
            // semaphoreD.release();
        }
    }
}
