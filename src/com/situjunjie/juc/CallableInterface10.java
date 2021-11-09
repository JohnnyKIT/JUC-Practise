package com.situjunjie.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable接口： 类比runnable
 *面试题:callable接口与runnable接口的区别？
 *
 *  答：（1）是否有返回值
 *        （2）是否抛异常
 *        （3）落地方法不一样，一个是run，一个是call
 *
 *        Callable 重要实现类 : FutureTask
 */
public class CallableInterface10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(()->{
            //Do something
            Thread.sleep(3000);
            return 1024;});
        new Thread(futureTask,"AA").start();

        while (!futureTask.isDone()){
            System.out.println("等待执行完成====>");
        }
        System.out.println("FutureTask执行完成，获取结果==>"+futureTask.get());
    }
    /**
     * /**
     *  *
     *  *
     * 在主线程中需要执行比较耗时的操作时，但又不想阻塞主线程时，可以把这些作业交给Future对象在后台完成，
     * 当主线程将来需要时，就可以通过Future对象获得后台作业的计算结果或者执行状态。
     *
     * 一般FutureTask多用于耗时的计算，主线程可以在完成自己的任务后，再去获取结果。
     *
     * 仅在计算完成时才能检索结果；如果计算尚未完成，则阻塞 get 方法。一旦计算完成，
     * 就不能再重新开始或取消计算。get方法而获取结果只有在计算完成时获取，否则会一直阻塞直到任务转入完成状态，
     * 然后会返回结果或者抛出异常。
     *
     * 只计算一次
     * get方法放到最后
     *  */



}
