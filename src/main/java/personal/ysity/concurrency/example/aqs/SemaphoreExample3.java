package personal.ysity.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample3 {

    private static int ThreadCount = 20;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i<ThreadCount;i++){
            final int ThreadNum = i;
            exec.execute(()->{
                try {
                    if (semaphore.tryAcquire()){  // 尝试获取一个许可（默认1个），若得到许可则执行，没有则抛弃
                        test(ThreadNum);
                        semaphore.release();
                    }
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
