package com.currency.demo.aqs;

import java.util.concurrent.*;

/**
 * @Auther: LQ
 * @Date: 2018/10/29 22:38
 * @Description:
 */
public class ThreadPoolExp {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
       /* for (int i = 0; i < 10; i++) {
            executorService.schedule(() -> {
                System.out.println("  run ");
            }, 2, TimeUnit.SECONDS);
        }
        executorService.shutdown();*/

        for (int i = 0; i < 10; i++) {
            final int x = i;
            ScheduledFuture<?> scheduledFuture = executorService.scheduleAtFixedRate(() -> {
                System.out.println("  run " + x);
            }, 2, 2, TimeUnit.SECONDS);
        }

    }
}
