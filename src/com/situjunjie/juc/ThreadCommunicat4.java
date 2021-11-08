package com.situjunjie.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.omg.IOP.Codec;


class ShareDataOne//资源类
{
    private int number = 0;//初始值为零的一个变量

    public synchronized void increment() throws InterruptedException
    {
        //1判断
        if(number !=0 ) {
            this.wait();
        }
        //2干活
        ++number;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //3通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException
    {
        // 1判断
        if (number == 0) {
            this.wait();
        }
        // 2干活
        --number;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3通知
        this.notifyAll();
    }
}

/**
 *
 * @Description:
 *现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。
 * @author xialei
 *
 *  * 笔记：Java里面如何进行工程级别的多线程编写
 * 1 多线程变成模板（套路）-----上
 *     1.1  线程    操作    资源类
 *     1.2  高内聚  低耦合
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知

 */
public class ThreadCommunicat4
{
    public static void main(String[] args)
    {
        ShareDataOne sd = new ShareDataOne();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.increment();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i < 10; i++) {
                try {
                    sd.decrement();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
/*
 * *
 * 2 多线程变成模板（套路）-----下
 *     2.1  判断
 *     2.2  干活
 *     2.3  通知
 * 3 防止虚假唤醒用while
 *
 *
 * */







