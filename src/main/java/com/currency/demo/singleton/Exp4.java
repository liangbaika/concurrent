package com.currency.demo.singleton;

import com.currency.demo.utils.Safe;
import com.currency.demo.utils.Unsafe;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 19:52
 * @Description:懒汉单例模式 不安全
 */
@Unsafe
public class Exp4 {

    private static Exp4 exp1;

    private Exp4() {
    }

    public static Exp4 getInstance() {
        if (exp1 == null) {
            synchronized (Exp1.class) {
                if (exp1 == null) {
                    exp1 = new Exp4();
                }
            }
        }
        return exp1;
    }
}
