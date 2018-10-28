package com.currency.demo.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: LQ
 * @Date: 2018/10/28 21:09
 * @Description: 比CountDownLatch更强大 计数器可以重置
 */
public class CyclicBarrierExp {

    private static int threadCount = 20;
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            Thread.sleep(1111);
            es.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            });
        }
        es.shutdown();
        System.out.println(" finish ");
    }

    private static void test(int num) throws Exception {
        Thread.sleep(850);
        System.out.println("  ready race threadNum is  " + num);
        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(" continue  " + num);
    }

}
