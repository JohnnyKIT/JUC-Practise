package com.situjunjie.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 第二节 Lock重要实现类 使用ReentrenceLock来卖票
 */
public class useReentranceLockSaleTicket2 {
    static class Ticket{
        static Integer num = 30;//票数

        public static ReentrantLock lock = new ReentrantLock();

        //卖票方法 , 使用ReentrenceLock 不用synchronized
        public static void sale(){
            lock.lock(); //开始操作资源前 锁住
            try {
                if(num>0){
                    num--;
                    System.out.println(Thread.currentThread().getName()+"卖出1张，剩余票数："+num);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //执行完成必须解锁
                lock.unlock();
            }


        }

    }

    public static void main(String[] args) {

        //创建3个线程
        Thread thread1 = new Thread(()->{
            for(int i=0;i<40;i++){
                reviewSynchronized.Ticket.sale();
            }
        },"AAA");
        Thread thread2 = new Thread(()->{
            for(int i=0;i<40;i++){
                reviewSynchronized.Ticket.sale();
            }
        },"BBB");
        Thread thread3 = new Thread(()->{
            for(int i=0;i<40;i++){
                reviewSynchronized.Ticket.sale();
            }
        },"CCC");

        //同时执行卖票方法
        thread1.start();
        thread2.start();
        thread3.start();
    }


}
