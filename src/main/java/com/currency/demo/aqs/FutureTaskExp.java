package com.currency.demo.aqs;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Auther: LQ
 * @Date: 2018/10/29 21:37
 * @Description:FutureTask类似future
 */
public class FutureTaskExp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(" do  something in calling ");
                Thread.sleep(3333);
                return "Done";
            }
        });
        new Thread(futureTask).start();
        System.out.println(" do sth in main ");
        String res = futureTask.get();
        System.out.println("  res:" + res);
    }


}
