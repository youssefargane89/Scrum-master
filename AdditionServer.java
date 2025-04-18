package org.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import addition.AdditionServiceGrpc;
import addition.Addition.AddRequest;
import addition.Addition.AddResponse;

import java.io.IOException;

public class AdditionServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new AdditionServiceImpl())
                .build()
                .start();

        System.out.println("Serveur gRPC prêt sur le port 50051...");
        server.awaitTermination();
    }
}

class AdditionServiceImpl extends AdditionServiceGrpc.AdditionServiceImplBase {
    @Override
    public void add(AddRequest request, StreamObserver<AddResponse> responseObserver) {
        int sum = request.getA() + request.getB();
        AddResponse response = AddResponse.newBuilder().setResult(sum).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

