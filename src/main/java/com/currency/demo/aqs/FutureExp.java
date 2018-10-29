package com.currency.demo.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Auther: LQ
 * @Date: 2018/10/29 21:31
 * @Description:future
 */
public class FutureExp {

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println(" do  something in calling ");
            Thread.sleep(3333);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        System.out.println(" do something else in main ");
        Thread.sleep(1111);
        System.out.println(" res: " + future.get());
        executorService.shutdown();
    }
}
