package personal.ysity.concurrency.example.singleton;

import personal.ysity.concurrency.annoations.NotRecommend;
import personal.ysity.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类加载的时候初始化
 * 问题：当构造函数中的有大量初始化工作时，系统性能会降低   2  当单例实例没有被使用的。浪费资源
 */
@ThreadSafe
@NotRecommend
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }
}
