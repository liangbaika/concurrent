package com.currency.demo.atomic;

import com.currency.demo.utils.Safe;
import com.currency.demo.utils.Unsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: 使用atomic包 达到线程安全（CAS原理）
 */
@Safe
public class Example2 {

    //模拟请求总数
    private static int clientTotal = 5000;

    //模拟同时并发线程数
    private static int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {

            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(" exe  complete!  count is :" + count.get());
    }

    private static void add() {
        count.incrementAndGet();
    }

}
