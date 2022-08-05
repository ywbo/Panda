
package org.ilearning.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;

/**
 * 线程相关 单元测试
 *
 * @author yuwenbo
 * @since 2022-08-04
 */
public class MyThreadTest {

    /**
     * 使用继承Thread类创建 测试
     */
    @Test
    public void test_ExtendsThread() {
        // 启动线程
        ExtendsThread t1 = new ExtendsThread();
        t1.setName("线程1");
        t1.start();

        ExtendsThread t2 = new ExtendsThread();
        t2.setName("线程2");
        t2.start();
    }

    /**
     * 实现Runnable接口创建线程 测试
     */
    @Test
    public void test_RunnableThread() {
        // 创建线程
        RunnableThread runnableThread = new RunnableThread();
        // 将Runnable实现类作为参数给Thread
        Thread t1 = new Thread(runnableThread);
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(runnableThread);
        t2.setName("线程2");
        t2.start();

    }

    /**
     * 实现Callable接口创建线程 测试
     */
    @Test
    public void test_CallableThread() {
        CallableThread callableThread = new CallableThread();
        FutureTask futureTask = new FutureTask(callableThread);
        new Thread(futureTask, "AAA").start();

        // 获取Callable中call() 的返回值
        try {
            Object sum = futureTask.get();
            System.out.println("总和为：" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现Callable接口创建多线程 测试
     */
    @Test
    public void test_CallableThreads() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableThreads());
        new Thread(futureTask, "AAA").start();
        new Thread(futureTask, "BBB").start();

        int a = 100;
        int b = 0;
        while (!futureTask.isDone()) {
            try {
                b = futureTask.get();
                System.out.println(Thread.currentThread().getName() + ":" + b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Result = " + (a + b));
    }

    /**
     * 使用线程池创建线程 测试
     */
    @Test
    public void test_ThreadPool() {
        // 1. 提供指定的线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        // 输出java.util.concurrent.ThreadPoolExecutor
        System.out.println(service.getClass());
        ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) service;
        // 自定义线程池的属性
        // poolExecutor.setCorePoolSize(15);
        // long toSeconds = TimeUnit.SECONDS.toSeconds(3);
        // poolExecutor.setKeepAliveTime(10L, toSeconds);

        // 2. 执行指定的线程操作。需要提供实现 Runnable 接口或者 Callable 接口实现类的对象
        service.execute(new ThreadPool("AAA"));
        service.execute(new ThreadPools("BBB"));

        // 3. 关闭连接池
        service.shutdown();
    }

    /**
     * 使用匿名类创建线程 测试
     */
    @Test
    public void test_Anonymous() {
        AnonymousInnerClassThread thread = new AnonymousInnerClassThread();
        thread.Anonymous();
    }

    /**
     * 给定T1，T2，T3三个线程，测试使用join()方法,它们的执行顺序
     */
    @Test
    public void test_OrderThread() {
        OrderThread threadA = new OrderThread();
        threadA.start();
        OrderThreadB orderThreadB = new OrderThreadB(threadA);
        Thread threadB = new Thread(orderThreadB);
        threadB.start();

        OrderThreadC threadC = new OrderThreadC(threadB);
        FutureTask futureTask = new FutureTask(threadC);
        new Thread(futureTask).start();
    }

    /**
     * 给定T1，T2，T3三个线程，测试使用信号量,它们的执行顺序
     */
    @Test
    public void test_SemaphoreThread() {
        Semaphore semaphoreD = new Semaphore(1);
        Semaphore semaphoreE = new Semaphore(1);
        Semaphore semaphoreF = new Semaphore(1);

        // 线程SemaphoreThread启动
        new SemaphoreThread(semaphoreD, semaphoreE).start();

        // 线程SemaphoreThreadE启动
        SemaphoreThreadE semaphoreThreadE = new SemaphoreThreadE(semaphoreE, semaphoreF);
        new Thread(semaphoreThreadE).start();

        // 线程SemaphoreThreadF启动
        SemaphoreThreadF semaphoreThreadF = new SemaphoreThreadF(semaphoreD, semaphoreF);
        FutureTask futureTask = new FutureTask(semaphoreThreadF);
        new Thread(futureTask).start();

    }
}
