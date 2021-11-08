package com.situjunjie.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 程序间通信 对标synchronized 的juc实现
 *
 * synchronized > Lock
 * wait  >  await
 * notifyAll > signal
 *
 * 只要涉及到线程之间需要配合 那么久需要通信
 * 问题：3个线程 打印AAAAABBBBBCCCCCAAAAABBBBBCCCCC..有序打印15轮
 */
public class ThreadComunicat6 {



    public static void main(String[] args) {

        Print p = new Print();

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                p.printA();
            }
        },"AAA").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                p.printB();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                p.printC();
            }
        },"CCC").start();

    }
}

class Print{
    //循环打印5次
    String str  = "A";
    public Lock lock = new ReentrantLock();
    //一个lock 创建 3把钥匙。
    Condition ca = lock.newCondition();
    Condition cb = lock.newCondition();
    Condition cc = lock.newCondition();
    public void print5(String str){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"===>"+str);
        }
    }
    public void printA(){
        lock.lock();
        try {
            while(str!="A"){
                ca.await();  //ca 再次等待
            }
            print5(str);
            str="B";
            cb.signalAll();  //通知cb的等待结束 开始干活
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while(str!="B"){
                cb.await();
            }
            print5(str);
            str="C";
            cc.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while(str!="C"){
                cc.await();
            }
            print5(str);
            str="A";
            ca.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

