package com.currency.demo.aqs;

import java.util.concurrent.*;

/**
 * @Auther: LQ
 * @Date: 2018/10/28 21:09
 * @Description: Semaphore 多个并发控制访问数
 */
public class SemaphoreExp {

    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService es = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            es.execute(() -> {
                try {
                    //尝试获取许可 可能会丢弃
                    if (semaphore.tryAcquire(2, TimeUnit.SECONDS)) {
                        test(threadNum);
                        //释放
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }
        es.shutdown();
        System.out.println(" finish ");
    }

    private static void test(int num) throws InterruptedException {
        System.out.println(" threadNum is  " + num);
        Thread.sleep(1250);
    }

}
