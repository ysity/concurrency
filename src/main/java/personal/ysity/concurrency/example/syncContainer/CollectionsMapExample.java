package personal.ysity.concurrency.example.syncContainer;

import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.ThreadSafe;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe

//使用Collections.synchronizedMap实例HashMap
public class CollectionsMapExample {
    private static Map<Integer,Integer> map  =Collections.synchronizedMap(new HashMap<>());
    private static int clientTotal = 5000;
    private static int ThreadTotal = 200;

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i < clientTotal ; i++){
            final int count = i;
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    uptate(count);
                    semaphore.release();
                }catch (InterruptedException e){
                    log.error("exception: " + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exec.shutdown();
        log.info("size: {}",map.size());
    }
    private static void uptate(int i){
        map.put(i,i);
    }
}
