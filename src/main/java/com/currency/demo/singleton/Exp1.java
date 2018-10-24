package com.currency.demo.singleton;

import com.currency.demo.utils.Safe;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 19:52
 * @Description:懒汉单例模式 volatile是关键 不加的话线程不安全
 */
@Safe
public class Exp1 {

    private volatile static Exp1 exp1;

    private Exp1() {
    }

    public static Exp1 getInstance() {
        if (exp1 == null) {
            synchronized (Exp1.class) {
                if (exp1 == null) {
                    exp1 = new Exp1();
                }
            }
        }
        return exp1;
    }

}
