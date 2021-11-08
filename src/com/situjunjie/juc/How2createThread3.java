package com.situjunjie.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 创建线程的方式
 */
public class How2createThread3 {


    public static void main(String[] args) {
        //第一种方式 继承Thread 实现run  这种方式不好 java是单继承，浪费继承关系了
//        ExntendThread thread1 = new ExntendThread();
//        ExntendThread thread2 = new ExntendThread();
//        ExntendThread thread3 = new ExntendThread();
//        thread1.start();
//        thread2.start();
//        thread3.start();

        //第二种 实现Runable接口  这种方法也不好 会新增类
//        ExntendThread t1 = new ExntendThread();
//        ExntendThread t2 = new ExntendThread();
//        ExntendThread t3 = new ExntendThread();
//        t1.start();
//        t2.start();
//        t3.start();

        //第三种 使用Lambda表达式创建Thread 比较简洁
        Thread t4 = new Thread(()->{
            while(Ticket.num>0){
                Ticket.sale();
            }
        },"AAA");
        Thread t5 = new Thread(()->{
            while(Ticket.num>0){
                Ticket.sale();
            }
        },"BBB");
        Thread t6 = new Thread(()->{
            while(Ticket.num>0){
                Ticket.sale();
            }
        },"CCC");
        t4.start();t5.start();t6.start();

        //TODO 最佳方法 线程池中拿线程
    }

    //第一种方式 继承Thread 实现run  这种方式不好 java是单继承，浪费继承关系了
    static class ExntendThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (Ticket.num>0){
                Ticket.sale();
            }
        }
    }

    //第二种 实现Runable接口
    static class ImplementRunable implements Runnable{

        @Override
        public void run() {
            while (Ticket.num>0){
                Ticket.sale();
            }
        }
    }




    //随便引入的一个资源类
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
}
