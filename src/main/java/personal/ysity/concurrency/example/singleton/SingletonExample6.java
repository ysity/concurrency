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
public class SingletonExample6 {

    private SingletonExample6(){

    }

    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    public static SingletonExample6 getInstance(){
        return instance;
    }
}
