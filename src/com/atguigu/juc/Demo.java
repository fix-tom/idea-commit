package com.atguigu.juc;

/**
 * Created by 休假先生 on 2020/4/7.
 */
class Print{
    private char chr='A';
    private Integer number=1;
    public synchronized  void  printNumber(){
        System.out.println(number);
        System.out.println(number+1);
        number+=2;
        notify();
        try {
            wait();
        }catch (Exception e){
            e.printStackTrace();
        }
    } public synchronized  void  printWord(){
        System.out.println(chr);
        chr+=1;
        notify();
        try {
            if (chr<='Z'){
                wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
public class Demo {
    public static void main(String[] args) {
        Print print = new Print();
        new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                print.printNumber();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                print.printWord();
            }
        },"B").start();
    }
}
