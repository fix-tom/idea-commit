package com.atguigu.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by 休假先生 on 2020/4/8.
 * semaphore 信号灯
 * 争车位
 * 服务限流：在构造方法里传入，限流的量
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=6 ; i++) {
           new Thread(()->{
               boolean flag=false;
               try {
                   semaphore.acquire();
                   flag=true;
                   System.out.println(Thread.currentThread().getName()+"\t 抢占座位");
                   TimeUnit.SECONDS.sleep(new Random().nextInt(3));
                   System.out.println(Thread.currentThread().getName()+"\t 离开座位");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }finally {
                 if (flag){
                     semaphore.release();
                 }
               }
           },String.valueOf(i)).start();
        }
    }
}
