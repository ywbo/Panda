
package org.ilearning.thread;

/**
 * 创建线程方式：线程池
 * </p>
 * 线程池好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 * 核心参数：
 * corePoolSize：核心池的大小
 * maximumPoolSize：最大线程数
 * keepAliveTime：线程没有任务时最多保持多长时间后会终止
 * </p>
 * 步骤：
 * 1.以实现Runnable接口或实现Callable的实现类创建好类
 * 2.实现run或call方法
 * 3.创建线程池
 * 4.调用线程池的execute方法执行某个线程，参数是之前实现Runnable或Callable接口的对象
 * </p>
 * 题目：遍历100以内的偶数
 *
 * @author yWX983890
 * @since 2022-08-04
 */
class ThreadPool implements Runnable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadPool(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(name + ":" + i);
            }
        }
    }
}

class ThreadPools implements Runnable {
    public ThreadPools(String name) {
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
