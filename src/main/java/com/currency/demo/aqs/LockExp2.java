package com.currency.demo.aqs;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: LQ
 * @Date: 2018/10/28 22:55
 * @Description:
 */
public class LockExp2 {

    private static final AtomicInteger num = new AtomicInteger(0);
    private static boolean flag = true;

    public static void main(String[] args) {

        final ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            while (flag) {

                lock.lock();
                System.out.println(" thread1  get a lock  ");
                System.out.println(" thread1 wait signal... ");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1  get singal .. ");
                System.out.println("thread1  end then realse lock .. ");
                lock.unlock();
            }

        }).start();


        new Thread(() -> {
            while (flag) {
                try {
                    Thread.sleep(1111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                System.out.println(" thread2  get a lock  ");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();
                System.out.println(" send a singal to all and num++ ");
                int i = num.incrementAndGet();
//                if (i >= 4) {
//                    flag = false;
//                }
                System.out.println("thread2  end then realse lock .. ");
                lock.unlock();
            }
        }).start();

    }
}
