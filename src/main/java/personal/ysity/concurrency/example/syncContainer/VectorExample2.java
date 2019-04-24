package personal.ysity.concurrency.example.syncContainer;

import personal.ysity.concurrency.annoations.NotThreadSafe;

import java.util.Vector;

@NotThreadSafe
public class VectorExample2 {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {     //恰好此时线程1运行到这里，，i=9,那么vector中的 i=9会被移除。此时线程2再get时就会出现越界异常
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {     //当线程2运行到这里时 ，假设i=9
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            ;
            thread2.start();
        }
    }
}
