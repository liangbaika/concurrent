package com.currency.demo.atomic;

import com.currency.demo.utils.Safe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: LongAddr类
 * AtomicLong:依靠底层的cas来保障原子性的更新过数据 在添加或减少时 不断的cas死循环到特定的值 从而更新数据
 * LongAddr:将之前单个节点的并发分散到各个节点，这样从而提高在高并发时候的效率
 * LongAdder在统计的时候如果有并发更新，可能导致统计的数据有误差
 */
@Safe
public class Example3 {

    //模拟请求总数
    private static int clientTotal = 5000;

    //模拟同时并发线程数
    private static int threadTotal = 200;

    private static LongAdder count = new LongAdder();

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
        System.out.println(" exe  complete!  count is :" + count);
    }

    private static void add() {
        count.increment();
    }

}
