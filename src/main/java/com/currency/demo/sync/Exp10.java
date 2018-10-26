package com.currency.demo.sync;

import com.currency.demo.utils.Unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LQ
 * @Date: 2018/10/26 22:38
 * @Description:ConcurrentModificationException 容易出错
 */
@Unsafe
public class Exp10 {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        test1(list);
        test2(list);

//        test3(list);


    }

    public static void test1(List<Integer> list) {
        for (Integer e : list) {
            if (e.equals(3)) {
                list.remove(e);
            }
        }
    }

    public static void test2(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(3)) {
                list.remove(i);
            }
        }

    }

    public static void test3(List<Integer> list) {
        //迭代器
    }
}
