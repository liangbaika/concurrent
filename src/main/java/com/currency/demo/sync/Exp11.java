package com.currency.demo.sync;

import com.currency.demo.utils.Unsafe;

import java.util.Vector;

/**
 * @Auther: LQ
 * @Date: 2018/10/26 22:50
 * @Description:Vector这类同步容器不一定 一定是线程安全的
 */
@Unsafe
public class Exp11 {
    private static Vector<Integer> vector = new Vector<>();


    public static void main(String[] args) throws InterruptedException {
            Thread thread3 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    vector.add(i);
                }
            });
            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    vector.remove(i);
                }
            });

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 100; i++) {
                    vector.get(i);
                }
            });
            thread3.start();
            thread2.start();
            thread1.start();
    }
}
