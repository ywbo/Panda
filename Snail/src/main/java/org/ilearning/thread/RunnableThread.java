
package org.ilearning.thread;

/**
 * 创建线程方式：实现 Runnable 接口
 * </p>
 * 步骤：
 * 1.创建一个实现了Runnable接口的类
 * 2.实现类去实现Runnable中的抽象方法：run()
 * 3.创建实现类的对象
 * 4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
 * 5.通过Thread类的对象调用start()
 * ① 启动线程
 * ②调用当前线程的run()–>调用了Runnable类型的target的run()
 * </p>
 * 题目：遍历100以内的所有偶数
 *
 * @author yWX983890
 * @since 2022-08-04
 */
public class RunnableThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
