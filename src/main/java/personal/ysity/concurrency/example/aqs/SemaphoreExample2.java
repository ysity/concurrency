package personal.ysity.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    private static int ThreadCount = 20;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i<ThreadCount;i++){
            final int ThreadNum = i;
            exec.execute(()->{
                try {
                    semaphore.acquire(3);  //获取多个许可
                    test(ThreadNum);
                    semaphore.release(3);  //释放多个许可
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
