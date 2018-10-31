package com.currency.demo.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: LQ
 * @Date: 2018/10/31 20:03
 * @Description: GuavaCache的使用
 */
@Slf4j
public class GuavaCache {

    public static void main(String[] args) throws Exception {
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .recordStats()
                .build();

        Integer value = cache.getIfPresent(1);
        Integer value2 = cache.get(1, new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return -1;
            }
        });
        log.info("value  {}  value2 {}", value, value2);
        cache.put(2, 222);
        for (int i = 0; i < 15; i++) {
            cache.put(i, i * 10);
        }
        for (int i = 0; i < 15; i++) {
            Integer v = cache.getIfPresent(i);
            log.info("{}", v);
        }
        log.info("{} {}", cache.stats().hitRate(), cache.stats().missRate());
    }
}