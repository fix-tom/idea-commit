package com.atguigu.juc;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by 休假先生 on 2020/4/8.
 * 第三种获取线程的方法,Callable
 */
class Date implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("A");
        return "OK";
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Date date = new Date();
        FutureTask futureTask = new FutureTask(new Date());
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
    }
}
