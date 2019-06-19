package com.nmq.nio.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * buffer 的scattering 和 gathering
 * 对于多个buffer[] scattering操作可以按照buffer[]的顺序去写入数据、写完第一个buffer写第二个。写完第二个写第三个。以此类推；
 * gathering操作可以按照buffer[]的顺序去读取数据，读完第一个buffer[0]读第二个，以此类推
 * @author niemengquan
 * @create 2019/6/18
 * @modifyUser
 * @modifyDate
 */
public class NioBufferScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = 8899;
        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocketChannel.bind(inetSocketAddress);
        // 定义buffer数组
        int totalLenth = 2 + 3 +4;
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        System.out.println("Server started. Listening port: " + port);
        SocketChannel socketChannel = serverSocketChannel.accept();
        int readToalLenth =0;
        while (readToalLenth < totalLenth){
            //不断的读取客户端的请求 scattering 操作
            long read = socketChannel.read(buffers);
            readToalLenth += read;
        }

        // 读取完成之后，将读取到的内容在发送给客户端 gathering操作
        // 在读取之前首先要进行flip操作
        Arrays.asList(buffers).stream().forEach(buffer -> buffer.flip());
        // 打印当前的buffers 数组的position、limit、capacity
        Arrays.asList(buffers).stream().map( buffer -> "Position: " + buffer.position() + ": Limit: "
                + buffer.limit() + "; Capacity: " + buffer.capacity()).forEach(System.out::println);

        int writeTotalLenth =0;
        while (writeTotalLenth < totalLenth) {
            // 不断的往客户端里写数据
            long write = socketChannel.write(buffers);
            writeTotalLenth += write;
        }
        System.out.println("--------------------------------------------------------------------");
        // 打印当前的buffers 数组的position、limit、capacity
        Arrays.asList(buffers).stream().map( buffer -> "Position: " + buffer.position() + ": Limit: "
                + buffer.limit() + "; Capacity: " + buffer.capacity()).forEach(System.out::println);

        // 清空buffers
        Arrays.asList(buffers).stream().forEach(buffer -> buffer.clear());
    }
}
