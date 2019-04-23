package personal.ysity.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.NotRecommend;
import personal.ysity.concurrency.annoations.NotThreadSafe;
@Slf4j
@NotThreadSafe
@NotRecommend

//对象逃逸：一种错误的发布，当一个对象还没构造完成时，就使它被其他线程给发现了
public class Escape {
    private int thisCanbeEscape = 0;

    public Escape(){
        new InnerClass();
    }

    private class InnerClass{

        public InnerClass(){
            log.info("result:{}" , Escape.this.thisCanbeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
