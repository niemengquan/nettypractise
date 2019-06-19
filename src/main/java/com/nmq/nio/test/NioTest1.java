package com.nmq.nio.test;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.security.SecureRandom;

/**
 * @author niemengquan
 * @create 2019/6/17
 * @modifyUser
 * @modifyDate
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        SecureRandom secureRandom = new SecureRandom();
        for (int i=0;i<buffer.capacity();i++){
            int randomInt = secureRandom.nextInt(20);
            buffer.put(randomInt);
        }
        buffer.flip();

        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }

    }
}
