import io.netty.util.NettyRuntime;

import java.io.File;
import java.nio.ByteBuffer;
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

    @org.junit.Test
    public void modifyFileDesc(){
        File file = new File("E:\\BaiduYunDownload\\刘晗·法律思维30讲（完结）");
        boolean directory = file.isDirectory();
        if(directory){
            File[] files = file.listFiles();
            for(File child:files){
                String childName = child.getName();
                System.out.println(childName);
            }
        }
    }
    @org.junit.Test
    public void intValueTest () {
        int max = 65536;
        System.out.println(max <<= 1);
        for ( int i = 512;i >0 ;i <<=1){
            System.out.println(i);
        }
        ByteBuffer.allocateDirect(1024);
    }
}
