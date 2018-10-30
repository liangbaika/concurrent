package com.currency.demo.deadlock;

/**
 * @Auther: LQ
 * @Date: 2018/10/30 20:21
 * @Description:死锁演示 只会输出 0 1 或者 1 0  由于死锁不会输出后边的00或11了
 */
public class DeadLock implements Runnable {

    public int flag = 1;
    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        if (flag == 1) {
            synchronized (o1) {
                System.out.println(" 1 ");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(" 11 ");
                }
            }
        }

        if (flag == 0) {
            synchronized (o2) {
                System.out.println(" 0 ");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(" 00 ");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.flag = 1;
        DeadLock deadLock2 = new DeadLock();
        deadLock2.flag = 0;

        new Thread(deadLock).start();
        new Thread(deadLock2).start();
    }
}
