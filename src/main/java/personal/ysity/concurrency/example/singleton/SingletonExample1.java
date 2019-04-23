package personal.ysity.concurrency.example.singleton;

import personal.ysity.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次创建的时候初始化
 */
@NotThreadSafe
public class SingletonExample1 {

    //私有的构造函数
    private SingletonExample1(){

    }

    //静态的单例实例
    private static SingletonExample1 instance = null;

    public static SingletonExample1 getInstance(){
        if (instance == null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
