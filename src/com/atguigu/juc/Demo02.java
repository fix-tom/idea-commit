package com.atguigu.juc;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 休假先生 on 2020/4/8.
 */
class DataShare{
    private char chr='A';
    private Integer number=1;
    private Integer total=2;
    private char helpChr='B';
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    private Condition conditionChr=lock.newCondition();
    public void printNumber(){
        lock.lock();
        try {
            if (number==total){
                condition.await();
            }
            System.out.println(number);
            System.out.println(number+1);
            number+=2;
            total=number;
            conditionChr.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
    public void printWord(){
        lock.lock();
        try {
            if (chr==helpChr){
                conditionChr.await();
            }
            System.out.println(chr);
            chr+=1;
            helpChr= chr;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}
public class Demo02 {
    public static void main(String[] args) {
        DataShare data = new DataShare();
        new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                data.printNumber();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                data.printWord();
            }
        },"B").start();
    }
}
