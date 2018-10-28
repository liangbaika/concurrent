package com.currency.demo.sync;

import com.currency.demo.utils.Safe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: ConcurrentSkipListSet 线程安全
 */
@Safe
public class ConcurrenySkipListExp {

    //模拟请求总数
    private static int clientTotal = 1000;

    //模拟同时并发线程数
    private static int threadTotal = 100;


    private static Set<Integer> list = new ConcurrentSkipListSet<>();


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
        list.add(i);
    }

}
