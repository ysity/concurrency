package personal.ysity.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@ThreadSafe
@Slf4j
public class ReentrantLockExample {

    private final static Lock lock = new ReentrantLock();
    private static int clientTotal = 5000;
    private static int ThreadTotal = 200;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException{
        ExecutorService execu = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i < clientTotal ; i++){
            execu.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (InterruptedException e){
                    log.error("exception: " + e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        execu.shutdown();
        log.info("count:{}",count);
    }
    private static void add(){
        lock.lock();
        try {
            count++;
        }finally {
            lock.unlock();
        }

        //count.getAndIncrement();
    }
}
