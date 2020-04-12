package com.atguigu.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 休假先生 on 2020/4/8.
 * synchronized是非公平锁
 * ReentrantLock默认是非公平锁，在构造方法上传入true变为公平锁
 * 当然绝对多数不会用到公平锁
 */
class BusTicket {
    private Integer number = 30;
    private ReentrantLock reentrantLock = new ReentrantLock(true);
    public void sale() {
        try {
            if (reentrantLock.tryLock(2L, TimeUnit.SECONDS)) {
                if (number > 0) {
                    System.out.println(Thread.currentThread().getName() + "\t卖出第" + (number--));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (reentrantLock.isHeldByCurrentThread())
                reentrantLock.unlock();
        }
    }
}

public class ReentrantLocklDemo {
    public static void main(String[] args) {
        BusTicket busTicket = new BusTicket();
        new Thread(()->{
            for (int i = 1; i <=30 ; i++) {
                busTicket.sale();
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 1; i <=30 ; i++) {
                busTicket.sale();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 1; i <=30 ; i++) {
                busTicket.sale();
            }
        }, "C").start();

    }
}

