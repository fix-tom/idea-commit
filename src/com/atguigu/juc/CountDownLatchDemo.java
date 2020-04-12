package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

/**
 * Created by 休假先生 on 2020/4/8.
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(7);
        for (int i = 1; i <=7 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 关教室门");
    }
}
