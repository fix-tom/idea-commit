package com.atguigu.juc;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 休假先生 on 2020/4/6.
 */
class Ticket{
    private Lock lock=new ReentrantLock();
    Condition condition = lock.newCondition();
    private int ticket=50;
    public  void sale(){
        lock.lock();
        try {
            Thread.sleep(100);
            if (ticket>0){
                System.out.println(Thread.currentThread().getName()+"\t"+ticket);
                ticket--;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
              lock.unlock();
        }
    }

}
public class SaleTicketDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Ticket ticket = new Ticket();
        try {
            for (int i = 1; i <=50 ; i++) {
                service.submit(()->{
                    ticket.sale();
                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            service.shutdown();
        }
//        new Thread(()->{
//            for (int i = 0; i <=30 ; i++) {
//                ticket.sale();
//            }
//        },"B").start();
//        new Thread(()->{
//            for (int i = 0; i <=30 ; i++) {
//                ticket.sale();
//            }
//        },"C").start();
//        new Thread(()->{
//            for (int i = 0; i <=30 ; i++) {
//                ticket.sale();
//            }
//        },"A").start();
    }
}
