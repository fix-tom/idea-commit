package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

/**
 * Created by 休假先生 on 2020/4/7.
 * 两个普通的同步方法，同一个手机,首选会执行发邮件、然后发短信
 * ……，一个方法中有延时，首先执行的是发邮件，因为synchronized关键字相当于把整个类给锁住了，一次只能进行一个操作
 * 一个换为static静态同步方法，首先执行的是发短息,因为synchronized关键锁了两个东西一个是，实例对象，static锁的是大的Class类
 * 两个都换成static静态同步方法，首先执行的是发邮件,锁的是同一个Class类
 *一个普通法和一个同步方法，首先执行的是hello，普通方法没有被synchronized锁住
 * 一个普通方法和一个静态方法，首先执行也是hello
 */
class Phone{

    public  synchronized void sendMessage() throws InterruptedException {

        System.out.println("发短息");
    }
    public  synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("发邮件");
    }
    public void hello(){
        System.out.println("hello");
    }
}
public class CurrentDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{

                phone.hello();
               // phone.sendMessage();

        },"B").start();


    }
}
