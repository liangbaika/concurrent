package com.currency.demo.immutable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 20:05
 * @Description: map内的值可变 map的引用不能改变指向  final 不可变对象
 */
public class Exp1 {

    private final static Map<String, String> map = new HashMap<>();

    static {
        //init
        map.put("1", "1111");
        map.put("2", "2222");
    }

    public static void main(String[] args) {
//        map=new HashMap<>();
        map.put("1", "1111111111");
        String value = map.get("1");
        System.out.println(value);
    }
}
