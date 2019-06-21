package com.nmq.nio.test;

import io.netty.buffer.ByteBuf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author niemengquan
 * @create 2019/6/21
 * @modifyUser
 * @modifyDate
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("localhost",8899));

        while (true){
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isConnectable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                        writeBuffer.flip();
                        try{
                            channel.write(writeBuffer);
                        }catch (IOException err){
                            System.out.println(channel + "连接已丢失！");
                            next.cancel();
                            channel.socket().close();
                            channel.close();
                        }
                        // 通过键盘输入模拟
                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(()->{
                            while (true){
                                try{
                                    writeBuffer.clear();
                                    BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(System.in));
                                    String sendMsg = bufferedInputStream.readLine();
                                    writeBuffer.put(sendMsg.getBytes());
                                    writeBuffer.flip();
                                    channel.write(writeBuffer);
                                }catch (Exception err){
                                    err.printStackTrace();
                                    System.out.println(channel + "连接已丢失！");
                                    next.cancel();
                                    channel.socket().close();
                                    channel.close();
                                }
                            }
                        });
                    }
                    // 注册可读事件
                    channel.register(selector,SelectionKey.OP_READ);
                }else if (next.isReadable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int count = channel.read(readBuffer);
                    if (count > 0){
                        readBuffer.flip();
                        Charset charset = Charset.forName("utf-8");
                        CharsetDecoder charsetDecoder = charset.newDecoder();
                        System.out.println(String.valueOf(charsetDecoder.decode(readBuffer).array()));
                    }
                }
                iterator.remove();
            }
        }
    }
}
