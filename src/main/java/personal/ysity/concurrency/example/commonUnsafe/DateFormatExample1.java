package personal.ysity.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.NotThreadSafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class DateFormatExample1 {
    //SimpleDateFormat 是线程不安全的，所以当其为全局变量时，在多线程情况下，会抛出异常
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private static int clientTotal = 5000;
    private static int ThreadTotal = 200;

    public static void main(String[] args) throws InterruptedException{
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i < clientTotal ; i++){
            exec.execute(()->{
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
        exec.shutdown();
    }
    private static void add(){
        try {
            simpleDateFormat.parse("20190424");
        }catch (ParseException e){
            log.error("ParseException : {}",e);
        }
    }
}
