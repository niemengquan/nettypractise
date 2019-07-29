package com.nmq.nio.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * 普通的handler
 * @author niemengquan
 * @create 2019/7/29
 * @modifyUser
 * @modifyDate
 */
public class Handler implements Runnable{
    final SocketChannel socket;
    final  SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static  final int READING = 0, SENDING =1;
    int state = READING;
    Handler(Selector selector, SocketChannel c) throws IOException {
        socket =c;
        c.configureBlocking(false);
        sk = socket.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete() {
        // writing your business
        return true;
    }
    boolean outputIsComplete() {
        // writing your business
        return true;
    }
    void process (){
        // writing your business
    }

    @Override
    public void run() {
        try {
            if (state == READING) read();
            else if (state == SENDING) send();
        }catch (Exception err){

        }
    }

    void read() throws Exception{
        socket.read(input);
        if (inputIsComplete()){
            process();
            state = SENDING;
            // Normally also do first write now
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }
    void send() throws Exception{
        socket.write(output);
        if (outputIsComplete()){
            sk.cancel();
        }
    }
}
