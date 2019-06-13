package com.nmq.grpc.proto;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * grpc helloword server
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class HelloWorldServer {
    private Server server;

    private void start() throws IOException {
        server = ServerBuilder.forPort(8899).addService(new GreeterImpl()).build().start();
        System.out.println("Server Started!");
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("exit jvm");
            HelloWorldServer.this.stop();
        }));
    }
    private void stop () {
        if (server != null){
            server.shutdown();
        }
    }
    private void blockUtilShutdown() throws InterruptedException {
        if (server != null){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HelloWorldServer helloWorldServer = new HelloWorldServer();
        helloWorldServer.start();
        helloWorldServer.blockUtilShutdown();
    }
}
