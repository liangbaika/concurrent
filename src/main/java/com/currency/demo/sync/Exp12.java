package com.currency.demo.sync;

import com.currency.demo.utils.Unsafe;

import java.util.Vector;

/**
 * @Auther: LQ
 * @Date: 2018/10/26 22:50
 * @Description: join  thread3,2,1 顺序执行
 */
public class Exp12 {
    private static Vector<Integer> vector = new Vector<>();


    public static void main(String[] args) throws InterruptedException {
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("33333**" + i);
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("1111**" + i);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("2222**" + i);
            }
        });
        thread3.start();
        thread3.join();
        thread2.start();
        thread2.join();
        thread1.start();
        thread1.join();
    }
}
