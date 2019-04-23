package personal.ysity.concurrency.example.singleton;

import personal.ysity.concurrency.annoations.NotRecommend;
import personal.ysity.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在类加载的时候初始化
 * 问题：当多线程时  由于synchronized关键字作用于方法，会使得性能大大降低
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    private SingletonExample3(){

    }

    private static SingletonExample3 instance = null;

    public static synchronized SingletonExample3 getInstance(){
        if (instance == null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
