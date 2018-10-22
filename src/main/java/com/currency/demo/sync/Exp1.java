package com.currency.demo.sync;

import lombok.extern.java.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 22:27
 * @Description: synchronized
 */
@Log
public class Exp1 {

    //synchronized 修饰代码块
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1  i:" + i);
            }
        }
    }

    // synchronized 修饰方法(此时实质上是修饰类)
    public static synchronized void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2  i:" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Exp1 exp1 = new Exp1();
        Exp1 exp2 = new Exp1();

        ExecutorService executorService = Executors.newCachedThreadPool();
        //顺序输出
        executorService.execute(() -> exp1.test2());
        executorService.execute(() -> exp1.test2());
        executorService.execute(() -> exp2.test2());
        Thread.sleep(1000);
        executorService.shutdown();
    }

}
