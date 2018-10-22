package com.currency.demo.atomic;

import com.currency.demo.utils.Safe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: AtomicReference
 */
@Safe
public class Example4 {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) throws InterruptedException {
        count.compareAndSet(0, 2);// 2
        count.compareAndSet(0, 1);// no
        count.compareAndSet(1, 3);// no
        count.compareAndSet(2, 4);// 4
        count.compareAndSet(3, 9);// 2
        System.out.println(" exe  complete!  count is :" + count.get());
    }


}
