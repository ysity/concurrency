package personal.ysity.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
public class AtomicExample5 {
    //AtomicIntegerFieldUpdater的作用是：更新AtomicExample5类的count字段的值，并且这个值只能是volatile修饰且不能是static修饰
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");
    @Getter
    private volatile int count = 100;
    public static void main(String[] args) {
        AtomicExample5 atomicExample5 = new AtomicExample5();
        if (updater.compareAndSet(atomicExample5,100,120)){
            log.info("update success 1 : {}" , atomicExample5.getCount());
        }
        if (updater.compareAndSet(atomicExample5,100,130)){
            log.info("update success 2 : {}" , atomicExample5.getCount());
        }else{
            log.info("update failed");
        }
    }
}
