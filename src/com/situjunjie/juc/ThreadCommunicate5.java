package com.situjunjie.juc;

/**
 * 线程间通信的虚假唤醒问题
 */
public class ThreadCommunicate5 {
    public static void main(String[] args) {

        Number number = new Number();

        //开启两个线程 不会发生问题
        new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    number.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    number.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    number.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<100;i++){
                try {
                    number.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
class Number{
    Integer i =0;

    public synchronized void add() throws InterruptedException {
        /**
         * 发生虚假唤醒的是在这里 要用while 判断 防止唤醒后直接接下去操作i 要继续判断一遍无问题再进行操作。
         * 要循环判断资源类
         */
        while(i==1){
            this.wait();
        }
            i++;
            System.out.println(Thread.currentThread().getName()+"===>"+i);
            this.notifyAll();

    }

    public synchronized void sub() throws InterruptedException {
        while(i==0){
            this.wait();
        }
            i--;
            System.out.println(Thread.currentThread().getName()+"===>"+i);
            this.notifyAll();

    }
}
