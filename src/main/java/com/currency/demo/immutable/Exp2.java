package com.currency.demo.immutable;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 20:05
 * @Description: unmodifiableMap  map内的值不可变 map的引用能改变指向
 */
public class Exp2 {

    private static Map<String, String> map = new HashMap<>();

    static {
        //init
        map.put("1", "1111");
        map.put("2", "2222");
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
//        throw  UnsupportedOperationException
//        map.put("1", "1111111111");
        String value = map.get("1");
        System.out.println(value);
    }
}
