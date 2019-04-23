package personal.ysity.concurrency.example.singleton;

import personal.ysity.concurrency.annoations.NotRecommend;
import personal.ysity.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在类加载的时候初始化
 * 由于synchronized加在方法内，所以在多线程情况下指挥使用一次同步锁
 * 问题 ：但是它是不安全的
 */
@ThreadSafe
@NotRecommend
public class SingletonExample5 {

    //私有的构造函数
    private SingletonExample5(){

    }

    //在多线程下：
    // 1. memory = allocate()  分配对象的内存空间
    // 2. ctorInstance()   初始化对象
    // 3. instance = memory 设置instance指向分配的内存空间



    //静态的单例对象   volatile + 双层检测机制 防止指令重排
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法
    public static SingletonExample5 getInstance(){
        if (instance == null){  //双重检测机制
            synchronized (SingletonExample5.class){  //同步锁
                if (instance == null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
