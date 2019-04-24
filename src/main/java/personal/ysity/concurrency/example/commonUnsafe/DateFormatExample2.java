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
public class DateFormatExample2 {
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
        log.info("simpleDateFormat: {}");
    }
    private static void add(){
        //将其设置为局部变量时，会因为堆栈封闭，每次调用方法的时候会重新创建一个新的对象，所以是安全的
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            simpleDateFormat.parse("20190424");
        }catch (ParseException e){
            log.error("ParseException : {}",e);
        }
    }
}
