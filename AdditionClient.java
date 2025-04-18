package org.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import addition.AdditionServiceGrpc;
import addition.Addition.AddRequest;
import addition.Addition.AddResponse;

public class AdditionClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        AdditionServiceGrpc.AdditionServiceBlockingStub stub =
                AdditionServiceGrpc.newBlockingStub(channel);

        AddResponse response = stub.add(
                AddRequest.newBuilder().setA(5).setB(4).build());

        System.out.println("Le résultat est : " + response.getResult());

        channel.shutdown();
    }
}

