package personal.ysity.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample1 {

    private static int ThreadCount = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(ThreadCount);
        for (int i = 0; i<ThreadCount;i++){
            final int ThreadNum = i;
            exec.execute(()->{
                try {
                    test(ThreadNum);
                }catch (Exception e){
                    log.error("exception:{}" , e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await(10,TimeUnit.MILLISECONDS);//只有当countDownLatch中执行的线程数减到才执行下面的代码
        log.info("finished");
        exec.shutdown();
    }

    private static void test(int ThreadNum) throws Exception{
        Thread.sleep(100);
        log.info("ThreadNum : {}",ThreadNum);
    }
}
