package com.currency.demo.sync;

import com.currency.demo.utils.Safe;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: CopyOnWriteArraySet 线程安全 适合读多写少的操作
 */
@Safe
public class CopyOnWriteArraySetExp {

    //模拟请求总数
    private static int clientTotal = 5000;

    //模拟同时并发线程数
    private static int threadTotal = 200;


    private static Set<Integer> list = new CopyOnWriteArraySet<>();


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
        System.out.println(" exe  complete!  count is :" + list.size());
    }

    private static void add() {
        list.add(1);
    }

}
