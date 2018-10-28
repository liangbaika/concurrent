package com.currency.demo.aqs;

import com.currency.demo.utils.Safe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: LQ
 * @Date: 2018/10/28 22:11
 * @Description:Lock
 */
@Safe
public class LockExp1 {
    //模拟请求总数
    private static int clientTotal = 5000;

    //模拟同时并发线程数
    private static int threadTotal = 200;

    private final static Lock lock = new ReentrantLock(true);

    private static Map<Integer, Integer> list = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int s = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(s);
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
        System.out.println(" exe  complete!  count is :" + list.size());
    }

    private static void add(int i) {
        lock.lock();
        try {
            list.put(i, i);
        } finally {
            lock.unlock();
        }
    }
}
