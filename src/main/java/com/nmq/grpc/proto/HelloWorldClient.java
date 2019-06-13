package com.nmq.grpc.proto;

import com.nmq.grpc.helloworld.GreeterGrpc;
import com.nmq.grpc.helloworld.HelloRequest;
import com.nmq.grpc.helloworld.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class HelloWorldClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",8899).usePlaintext().build();
        GreeterGrpc.GreeterBlockingStub greeterBlockingStub = GreeterGrpc.newBlockingStub(channel);
        for (int i=0;i<100;i++){
            HelloResponse helloResponse = greeterBlockingStub.sayHello(HelloRequest.newBuilder().setName("hello").build());
            String message = helloResponse.getMessage();
            System.out.println("Server repsponse:" + message);
        }
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}
