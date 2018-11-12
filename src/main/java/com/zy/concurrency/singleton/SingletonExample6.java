package com.zy.concurrency.singleton;

import com.zy.concurrency.annotations.ThreadSafe;

/**
 * Created by Horizon Time: 下午1:35 2018/11/11 Description:饿汉模式，单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample6 {

    // 私有构造函数
    private SingletonExample6() {

    }


    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }

}
