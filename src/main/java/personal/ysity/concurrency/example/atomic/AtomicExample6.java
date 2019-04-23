package personal.ysity.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class AtomicExample6 {
    //AtomicBoolean 可以使某个方法只执行一次
    private static AtomicBoolean isHappened = new AtomicBoolean(false);
    private static int clientTotal = 5000;
    private static int ThreadTotal = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService execu = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i < clientTotal ; i++){
            execu.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (InterruptedException e){
                    log.error("exception: " + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        execu.shutdown();
        log.info("isHappened:{}",isHappened.get());
    }

    private static void test(){
        if (isHappened.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
