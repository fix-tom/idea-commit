package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 休假先生 on 2020/4/5.
 */
//第一步创建资源类
    class ShareDataOne{
        private  int number=0;
        Lock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
        public  void incr() throws InterruptedException {
            lock.lock();
            //判断

            try {

                while (number!=0){
                   this.condition.await();
                }
                number++;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                this.condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            //干活

            //通知

        }
        public  void decr() throws InterruptedException {
            lock.lock();

            try {
                while (number!=1){
                  condition.await();
                }
                number--;
                System.out.println(Thread.currentThread().getName()+"\t"+number);
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }
}
public class NotifyWaitDemo {
    public static void main(String[] args) {
        ShareDataOne shareDataOne = new ShareDataOne();
        new Thread(() ->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

          new Thread(() ->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

  new Thread(() ->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

  new Thread(() ->{
            for (int i = 1; i <=10 ; i++) {
                try {
                    shareDataOne.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();



    }
}
