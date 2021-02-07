package com.yilefeng;

/**
 * 适用 synchronized
 *
 *
 */
public class SynchronizedDemo {

    private static class Worker {
        private volatile Integer value = null;
        private Object lock = new Object();

        public void sum() {
            synchronized (lock) {
                value = SynchronizedDemo.sum();
                lock.notifyAll();
            }
        }

        public Integer get() throws InterruptedException {
            synchronized (lock) {
                while (value == null) {
                    lock.wait();
                }
                return value;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        Worker worker = new Worker();
        // 在这里创建一个线程或线程池，
        new Thread(() -> worker.sum()).start();
        // 异步执行 下面方法

        int result = worker.get();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
