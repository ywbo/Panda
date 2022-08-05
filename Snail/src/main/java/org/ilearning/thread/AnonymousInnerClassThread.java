
package org.ilearning.thread;

/**
 * 创建线程方式：匿名内部类实现线程
 *
 * @author yuwenbo
 * @since 2022-08-05
 */
class AnonymousInnerClassThread {

    public void Anonymous() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程开始启动......");
                for (int i = 0; i <= 30; i++) {
                    System.out.println("run i:" + i);
                }
            }

        });
        thread.start();
    }
}
