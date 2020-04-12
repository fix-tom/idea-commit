package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by 休假先生 on 2020/4/7.
 *ArrayList 是线程不安全的 ，当然Vector是线程安全的，应为它的底层有synchronized关键字，Collections集合工具类也要让线程安全的方法。
 * CopyOnWriterArrayList 是线程安全的，读写分离，写时复制
 * HashMao 是线程不全的
 * ConcurrentHaspMap是线程安全的
 */
public class CurrentList {
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 1; i <=30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 9));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 1; i <=30 ; i++) {
//            new Thread(()->{
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }
    }

}
