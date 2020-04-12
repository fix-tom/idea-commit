package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by 休假先生 on 2020/4/8.
 *读写锁
 * 写不可以共享，读可以共享
 * 创建 ReentrantReadWriterLock，可以对读写分别加锁
 * 不加锁 不可以，写的时候原子性被破坏
 * 加ReentratLock 写控制了，但是没有并发度，不能读共享
 * 加读写锁ReentrantReadWriteLock
 */
class DataBase{
    volatile Map<String,String> map =new HashMap<>();
    private ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void write(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 准备写入");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 完成写入"+value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void read(String key){
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 准备读取");
        String result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 完成读取"+result);
    }
}
public class ReadAndWriter {
    public static void main(String[] args) {
        DataBase data = new DataBase();
        for (int i = 1; i <=10 ; i++) {
            final int tmpI=i;
            new Thread(()->{
               data.write(String.valueOf(tmpI),String.valueOf(tmpI) );
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=10 ; i++) {
            final int tmpI=i;
            new Thread(()->{
               data.read(String.valueOf(tmpI));
            },String.valueOf(i)).start();
        }

    }
}
