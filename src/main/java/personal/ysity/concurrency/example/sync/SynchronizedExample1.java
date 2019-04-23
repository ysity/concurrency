package personal.ysity.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    public void test1(int j){
        synchronized (this){
            for (int i = 0; i < 10 ; i++){
                log.info("test1 -j- i :{}- {}" , i,j);
            }
        }
    }

    public synchronized void test2(int j){
        for (int i = 0; i < 10 ; i++){
            log.info("test2 - i-j : {}-{}" , i,j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(()->{
            example1.test1(1);
        });
        exec.execute(()->{
            example2.test1(2);
        });
//        exec.execute(()->{
//            example1.test2(1);
//        });
//        exec.execute(()->{
//            example1.test2(2);
//        });
    }
}
