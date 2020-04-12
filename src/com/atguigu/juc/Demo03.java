package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 休假先生 on 2020/4/8.
 */
class Data{
    private Integer flag=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void print5(){
        lock.lock();
        try {
            while (flag!=1){
                c1.await();
            }
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }

      public void print10(){
        lock.lock();
        try {
            while (flag!=2){
                c2.await();
            }
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }

      public void print15(){
        lock.lock();
        try {
            while (flag!=3){
                c3.await();
            }
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            flag=1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }
        lock.unlock();
    }


}
public class Demo03 {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                data.print5();
            }

        },"A").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                data.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 1; i <=10 ; i++) {
                data.print15();
            }
        },"C").start();
    }
}
