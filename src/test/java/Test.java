import io.netty.util.NettyRuntime;

import java.util.HashSet;
import java.util.Set;
import java.util.Spliterator;

/**
 * @author niemengquan
 * @create 2019/6/19
 * @modifyUser
 * @modifyDate
 */
public class Test {
    @org.junit.Test
    public void test(){
        Set<String> set = new HashSet<>();
        set.add("zhangsan");
        set.add("lisi");
        System.out.println(set.size());
        Spliterator<String> spliterator = set.spliterator();
        int characteristics = spliterator.characteristics();
        System.out.println(characteristics);
        System.out.println("available processors：" + Runtime.getRuntime().availableProcessors());
        System.out.println("available processors：" + NettyRuntime.availableProcessors());
    }
}
