package com.nmq.nio.test;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author niemengquan
 * @create 2019/6/17
 * @modifyUser
 * @modifyDate
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception{
        FileInputStream inputStream = new FileInputStream("test.txt");
        FileChannel channel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        while (channel.read(byteBuffer)>0){
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();
        }
        inputStream.close();
    }
}
