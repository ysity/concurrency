package personal.ysity.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class NewFixedExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i=0;i<10;i++){
            final int index = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("index:{}",index);
                }
            });
        }
        executor.shutdown();
    }
}
