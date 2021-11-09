package com.situjunjie.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程不安全例子和三个解决方案
 */
public class ThreadNoSafeDemo7 {

    public static void main(String[] args) {


        /**
         * ArrayList集合add是不安全的，可以点开源码看看，没有synchronized，开线程同时插入元素会报ConcurrentModificationException
         */
//        List list = new ArrayList<String>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                    list.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(list);
//            }).start();
//        }

        /**
         * 解决集合线程安全问题：
         * 1、Vector 所有方法带synchronized  是线程安全的  一般多线程场景才用，性能没ArrayList好
         */
//        List vector = new Vector<String>();
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                vector.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(vector);
//            }).start();
//        }

        /**
         * 解决方法2：
         * List<String> list = Collections.synchronizedList(new ArrayList<>());
         * Collections提供了方法synchronizedList保证list是同步线程安全的
         *
         * 那HashMap，HashSet是线程安全的吗？也不是
         * 所以有同样的线程安全方法
         */

//        List list1 = Collections.synchronizedList(new ArrayList<String>());
//        for (int i = 0; i < 30; i++) {
//            new Thread(()->{
//                list1.add(UUID.randomUUID().toString().substring(0,8));
//                System.out.println(list1);
//            }).start();
//        }

        /**
         * 解决方法3：
         * CopyOnWirteArrayList 写时复制的ArrayList
         */
        List list2 = new CopyOnWriteArrayList<String>();

            for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list2);
            }).start();
        }
    }

}
