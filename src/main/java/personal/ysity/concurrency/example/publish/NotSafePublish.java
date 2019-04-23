package personal.ysity.concurrency.example.publish;

import lombok.extern.slf4j.Slf4j;
import personal.ysity.concurrency.annoations.NotThreadSafe;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class NotSafePublish {
    private String[] states = new String[]{"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        //发布对象：一个对象能够被当前范围之外的代码所使用
        //对象notSafePublish的私有属性被其他线程通过getStates方法，可以随意修改属性值
        NotSafePublish notSafePublish = new NotSafePublish();
        log.info("states : {}" , Arrays.toString(notSafePublish.getStates()));
        notSafePublish.getStates()[0] = "d";
        log.info("states : {}" , Arrays.toString(notSafePublish.getStates()));
    }
}
