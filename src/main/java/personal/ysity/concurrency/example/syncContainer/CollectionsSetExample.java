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

//使用Collections.synchronizedSet实例HashSet
public class CollectionsSetExample {
    private static Set<Integer> set  =Collections.synchronizedSet(new HashSet<>());
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
        log.info(" set-size: {}",set.size());
    }
    private static void uptate(int i){
        set.add(i);
    }
}
