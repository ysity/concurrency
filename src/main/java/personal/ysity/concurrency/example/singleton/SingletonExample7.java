package personal.ysity.concurrency.example.singleton;

import personal.ysity.concurrency.annoations.Recommend;
import personal.ysity.concurrency.annoations.ThreadSafe;

/**
 * 枚举模式 ： 最安全的
 */

@ThreadSafe
@Recommend
public class SingletonExample7 {
    //私有的构造函数
    private SingletonExample7(){

    }

    private static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只被调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
