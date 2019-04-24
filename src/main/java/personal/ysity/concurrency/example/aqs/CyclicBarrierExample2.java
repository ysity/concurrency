package personal.ysity.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample2 {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("callback is running");  //会在屏障满后执行
    });
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i=0;i<10;i++){
            final int ThreadNum = i;
            Thread.sleep(1000);
            exec.execute(()->{
                try {
                    race(ThreadNum);
                }catch (Exception e){
                    log.error("exception : {}" ,e);
                }
            });
        }
        exec.shutdown();
    }

    private static void race(int ThreadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(100);
        log.info("{} is ready",ThreadNum);
        cyclicBarrier.await();
        log.info("{} continue",ThreadNum);
    }
}
