
package org.ilearning.thread;

import java.util.concurrent.Callable;

/**
 * 创建线程方式：实现 Callable 接口
 * </p>
 * 步骤：
 * 1.创建一个实现Callable的实现类
 * 2.实现call方法，将此线程需要执行的操作声明在call()中
 * 3.创建Callable接口实现类的对象
 * 4.将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask的对象
 * 5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
 * 6.获取Callable中call方法的返回值
 * </p>
 * 实现Callable接口的方式创建线程的强大之处
 * call()可以有返回值的
 * call()可以抛出异常，被外面的操作捕获，获取异常的信息
 * Callable是支持泛型的
 * </p>
 * 题目：将100以内的偶数求和
 *
 * @author yWX983890
 * @since 2022-08-04
 */
class CallableThread implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
