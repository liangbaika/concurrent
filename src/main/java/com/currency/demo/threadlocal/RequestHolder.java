package com.currency.demo.threadlocal;

/**
 * @Auther: LQ
 * @Date: 2018/10/24 20:23
 * @Description:ThreadLocal 线程安全
 */
public class RequestHolder {
    private static final ThreadLocal<Long> holder = new ThreadLocal<>();

    public static void add(Long id) {
        holder.set(id);
    }

    public static Long get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
