package personal.ysity.concurrency.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import personal.ysity.concurrency.annoations.ThreadSafe;

@ThreadSafe
//Immutable 不可变对象
public class ImmutableExample1 {
    private final static ImmutableList list = ImmutableList.of(1,2,3);
    private final static ImmutableSet set = ImmutableSet.copyOf(list);
    private final static ImmutableMap<Integer,Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer,Integer> map2 =
            ImmutableMap.<Integer,Integer>builder().put(1,2).put(3,4).build();

    public static void main(String[] args) {
        //list.add(4); 不可用
        //set.add();  不可用
    }
}
