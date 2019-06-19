package com.nmq.nio.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * @author niemengquan
 * @create 2019/6/17
 * @modifyUser
 * @modifyDate
 */
public class NioReadChineseTest {
    public static void main(String[] args) {
        readChineseCorrect();
        readChineseError();
    }

    public static void readChineseCorrect(){
        try {
            FileInputStream fis = new FileInputStream("chinese.txt");
            //获取文件读写通道
            FileChannel channel = fis.getChannel();
            long length = new File("chinese.txt").length();
            MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            //获取字符集编码对象
            Charset charset = Charset.forName("utf-8");
            System.out.print(String.valueOf(charset.decode(mappedByteBuffer).array()));
            fis.close();
        }catch (Exception err){
            err.printStackTrace();
        }
    }

    public static void readChineseError(){
        try {
            FileInputStream fis = new FileInputStream("chinese.txt");
            //获取文件读写通道
            FileChannel channel = fis.getChannel();
            //创建字节缓冲区;UTF-8格式的编码使用三个字节来表示一个中文字符，如果你的文件中是中英文混排的那么下面的程序将会无法正确的解析。
            ByteBuffer bf = ByteBuffer.allocate(3);

            //获取字符集编码对象;
            Charset charset = Charset.forName("utf-8");
            //通过通道写入缓存
            while (true) {
                int readLength = channel.read(bf);
                if (readLength == -1){
                    break;
                }
                //调用flip()方法变为读模式
                bf.flip();
                System.out.print(String.valueOf(charset.decode(bf).array()));
                bf.clear();
            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
