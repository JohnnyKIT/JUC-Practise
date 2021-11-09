package com.situjunjie.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合的线程安全解决办法： 写时复制。类似数据库的读写分离
 *
 * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
 * 而是先将当前容器Object[]进行Copy，复制出一个新的容器Object[] newElements，然后向新的容器Object[] newElements里添加元素。
 * 添加元素后，再将原容器的引用指向新的容器setArray(newElements)。
 * 这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
 * 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 */
public class ThreadNoSafeDemo8 {

    public static void main(String[] args) {

        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();

        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();

        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();

    }
}
