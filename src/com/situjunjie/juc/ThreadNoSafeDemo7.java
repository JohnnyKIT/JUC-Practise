package com.situjunjie.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 线程不安全
 */
public class ThreadNoSafeDemo7 {

    public static void main(String[] args) {


        /**
         * ArrayList集合add是不安全的，可以点开源码看看，没有synchronized，开线程同时插入元素会报ConcurrentModificationException
         */
        List list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                    list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }

}
