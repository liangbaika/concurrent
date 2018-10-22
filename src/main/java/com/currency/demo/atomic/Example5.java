package com.currency.demo.atomic;

import com.currency.demo.utils.Safe;
import lombok.Getter;
import lombok.extern.java.Log;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Auther: LQ
 * @Date: 2018/10/22 21:39
 * @Description: AtomicIntegerFieldUpdater 更新一个实例的某个字段 这个字段必须用volatile 修饰 并且不能为static修饰
 */
@Safe
@Log
public class Example5 {

    private static AtomicIntegerFieldUpdater<Example5> updater = AtomicIntegerFieldUpdater.newUpdater(Example5.class, "count");

    @Getter
    public    volatile int count = 100;


    public static void main(String[] args) throws InterruptedException {
         Example5 example5 = new Example5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update  success 1 {}" + example5.getCount());
        }
        if (updater.compareAndSet(example5, 120, 130)) {
            log.info("update  success 2 {}" + example5.getCount());
        }

        if (updater.compareAndSet(example5, 120, 133)) {
            log.info("update  success 2 {}" + example5.getCount());
        } else {
            log.info("can not reach hear if3");
        }

    }


}
