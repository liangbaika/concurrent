package com.currency.demo.limit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Auther: LQ
 * @Date: 2018/10/31 22:16
 * @Description: RateLimiter限流
 */
public class LimitExp {

    private static final RateLimiter limiter = RateLimiter.create(5);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
          /*  if (limiter.tryAcquire()) {
                System.out.println(" call me ");
            }*/
            limiter.acquire();
            System.out.println(" call me ");
        }

    }
}
