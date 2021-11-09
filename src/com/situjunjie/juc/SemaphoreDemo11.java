package com.situjunjie.juc;

import java.util.concurrent.Semaphore;

/**
 * JUC常用辅助类
 *
 * Semaphore 信号量
 * 在信号量上我们定义两种操作：
 *  * acquire（获取） 当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），
 *  *             要么一直等下去，直到有线程释放信号量，或超时。
 *  * release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 *  *
 *  * 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 *
 */
public class SemaphoreDemo11 {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);  //三个停车位

        for (int i = 0; i < 6; i++) {  //开6台车抢车位
            new Thread(()->{
                try {
                    semaphore.acquire(); //获取一个停车位
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    Thread.sleep(3000); //停车3秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"走了");
                }
            },i+"号").start();
        }

    }
}
