package personal.ysity.concurrency.example.concurrent;



import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.ThreadSafe;

import java.util.List;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {

    private static List<Integer> list  = new CopyOnWriteArrayList<>();
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
        log.info("size: {}",list.size());
    }
    private static void uptate(int i){
        list.add(i);
    }
}
