package com.currency.demo.test;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: LQ
 * @Date: 2018/11/1 00:00
 * @Description: 演示 不同的list在新增删除 读取上的差异  一个小细节性能相差千百倍
 */
public class ListExp {

    public static void main(String[] args) {

        List<String> arrayList = Lists.newArrayList();
        List<String> linkedList = Lists.newLinkedList();
        for (int i = 0; i < 40000; i++) {
            arrayList.add(i+"!");
            linkedList.add(i+"!");
        }
        test(arrayList);
        test(linkedList);

    }

    private static void test(List<String> list) {

        long start = System.currentTimeMillis();

        for (int i = 0; i <40000; i++) {
            list.get(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(" cost time : " + (end - start));
    }
}
