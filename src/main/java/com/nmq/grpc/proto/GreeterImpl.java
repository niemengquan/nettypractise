package com.nmq.grpc.proto;

import com.nmq.grpc.helloworld.GreeterGrpc;
import com.nmq.grpc.helloworld.HelloRequest;
import com.nmq.grpc.helloworld.HelloResponse;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * grpc protocbuf implement
 * @author niemengquan
 * @create 2019/6/12
 * @modifyUser
 * @modifyDate
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {
    /**
     * Unary RPCs where the client sends a single request to the server and gets a single response back,
     * just like a normal function call
     * @param request
     * @param responseObserver
     */
    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Received client invoke sayHello: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage("Hello " + request.getName()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onCompleted();
    }

    /**
     * Server streaming RPCs where the client sends a request to the server and gets a stream to read a sequence of messages back.
     * The client reads from the returned stream until there are no more messages. gRPC guarantees message ordering within an individual RPC call.
     * @param request
     * @param responseObserver
     */
    @Override
    public void streamResponse(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println("Received client invoke streamResponse method: " + request.getName());
        HelloResponse helloResponse = HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build();
        responseObserver.onNext(helloResponse);
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onNext(HelloResponse.newBuilder().setMessage(UUID.randomUUID().toString()).build());
        responseObserver.onCompleted();
    }
}
