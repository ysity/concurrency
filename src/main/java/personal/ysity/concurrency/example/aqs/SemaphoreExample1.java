package personal.ysity.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SemaphoreExample1 {

    private static int ThreadCount = 20;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i<ThreadCount;i++){
            final int ThreadNum = i;
            exec.execute(()->{
                try {
                    semaphore.acquire();
                    test(ThreadNum);
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception:{}" , e);
                }
            });
        }
        exec.shutdown();
    }

    private static void test(int ThreadNum) throws Exception{
        log.info("ThreadNum : {}",ThreadNum);
        Thread.sleep(1000);
    }
}
