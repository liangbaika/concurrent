package com.currency.demo.singleton;

import com.currency.demo.utils.Safe;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 19:52
 * @Description:枚举单例模式
 */
@Safe
public class Exp3 {


    private Exp3() {
    }

    public static Exp3 getIntance() {
        return Holder.Instance.getInstance();
    }

    public enum Holder {
        Instance;
        private Exp3 exp1;
        //jvm 保证只调用一次
        Holder() {
            exp1 = new Exp3();
        }
        public Exp3 getInstance() {
            return exp1;
        }
    }
}
