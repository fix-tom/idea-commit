package com.atguigu.juc;

/**
 * Created by 休假先生 on 2020/4/7.
 * 多线程编程是在高内聚低耦合的前提下，线程操作资源类
 * 交互线程：判断、干活、通知
 * 注意防范虚假唤醒、所以就sync块尽量少用if一次性判断的、改用while循环判断的，主要原因还是线程在哪睡在哪醒
 */
class ShareData{
    private int number=0;
    public synchronized void incr() throws InterruptedException {
        while (number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
    public synchronized void decr() throws InterruptedException {
        while (number==0){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        this.notifyAll();
    }
}
public class Sync {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 1; i <=10 ; i++) {
            new Thread(() -> {

                try {
                    Thread.sleep(500);
                    shareData.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "A").start();
        }
        for (int i = 1; i <=10 ; i++) {
            new Thread(() -> {

                try {
                    Thread.sleep(500);
                    shareData.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }, "B").start();
        }

// new Thread(()->{
//                for (int i = 1; i <=10 ; i++) {
//                try {
//                    Thread.sleep(500);
//                    shareData.incr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                }
//            },"C").start();
//
//
//            new Thread(()->{
//                for (int i = 1; i <=10 ; i++) {
//                try {
//                    Thread.sleep(500);
//                    shareData.decr();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                }
//            },"D").start();


    }
}
