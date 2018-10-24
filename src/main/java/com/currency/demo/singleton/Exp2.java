package com.currency.demo.singleton;

import com.currency.demo.utils.Safe;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 19:52
 * @Description:饿汉单例模式
 */
@Safe
public class Exp2 {

    //类加载机制 天然线程安全
    private static Exp2 exp1 = new Exp2();

    private Exp2() {
    }

    public static Exp2 getInstance() {
        return exp1;
    }

}
