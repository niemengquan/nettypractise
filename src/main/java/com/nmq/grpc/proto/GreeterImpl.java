package com.nmq.grpc.proto;

import com.nmq.grpc.helloworld.GreeterGrpc;
import com.nmq.grpc.helloworld.HelloRequest;
import com.nmq.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;

/**
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Received client: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }
}
