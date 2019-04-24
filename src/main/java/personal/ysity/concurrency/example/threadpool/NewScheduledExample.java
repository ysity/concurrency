package personal.ysity.concurrency.example.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class NewScheduledExample {
    public static void main(String[] args) {

        //创建
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        /*
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        },3, TimeUnit.SECONDS);
        */
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        },1,3,TimeUnit.SECONDS);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer run");
            }
        }, new Date(),5000);
    }
}
