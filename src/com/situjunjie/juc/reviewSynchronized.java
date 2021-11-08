package com.situjunjie.juc;

/**
 * 用卖票来自复习最基本的Synchronized使用
 *
 */
public class reviewSynchronized {

    static class Ticket{
        static Integer num = 30;//票数

        //卖票方法 , 不加上synchronized就会出现超卖现象
        public static synchronized void sale(){
            if(num>0){
                num--;
                System.out.println(Thread.currentThread().getName()+"卖出1张，剩余票数："+num);
            }
        }

    }

    public static void main(String[] args) {

        //创建3个线程
        Thread thread1 = new Thread(()->{
            for(int i=0;i<40;i++){
                Ticket.sale();
            }
        },"AAA");
        Thread thread2 = new Thread(()->{
            for(int i=0;i<40;i++){
                Ticket.sale();
            }
        },"BBB");
        Thread thread3 = new Thread(()->{
            for(int i=0;i<40;i++){
                Ticket.sale();
            }
        },"CCC");

        //同时执行卖票方法
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
