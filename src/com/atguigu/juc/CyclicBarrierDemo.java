package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by 休假先生 on 2020/4/8.
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName() + "\t 召唤神龙");
        });
        for (int i = 1; i <= 7; i++) {
            final int tmpI=i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 收到的龙珠"+tmpI);
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
