package com.grpc.demo.calculator.client;

import com.proto.calculator.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CalculatorClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        CalculatorServiceGrpc.CalculatorServiceBlockingStub calculatorClient = CalculatorServiceGrpc.newBlockingStub(channel);


        // Unary
//        SumRequest sumRequest = SumRequest.newBuilder()
//                .setFirstNumber(10)
//                .setSecondNumber(20)
//                .build();
//
//        SumResponse sumResponse = calculatorClient.sum(sumRequest);
//
//        System.out.println(sumRequest.getFirstNumber() + " + " + sumRequest.getSecondNumber() + " = " + sumResponse.getSumResult());


        // Server Streaming
//        Long number = 5678908123123123137L;
//        PrimeNumberDecompositionRequest request = PrimeNumberDecompositionRequest.newBuilder()
//                .setNumber(number)
//                .build();
//        calculatorClient.primeNumberDecomposition(request)
//                .forEachRemaining(primeNumberDecompositionResponse -> {
//                    System.out.println(primeNumberDecompositionResponse.getPrimeFactor());
//                });


        // Client Streaming
//        CalculatorServiceGrpc.CalculatorServiceStub asyncClient = CalculatorServiceGrpc.newStub(channel);
//
//        CountDownLatch latch = new CountDownLatch(1);
//
//        StreamObserver<ComputeAverageRequest> requestStreamObserver = asyncClient.computeAverage(new StreamObserver<ComputeAverageResponse>() {
//            @Override
//            public void onNext(ComputeAverageResponse value) {
//                System.out.println(value.getAverage());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//                latch.countDown();
//            }
//        });
//
//        for (int i = 0; i < 1000; ++i) {
//            requestStreamObserver.onNext(ComputeAverageRequest.newBuilder().setNumber(i).build());
//        }
//
//        requestStreamObserver.onCompleted();
//
//        try {
//            latch.await(3, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        // Bi-directional Streaming
//        CalculatorServiceGrpc.CalculatorServiceStub asyncClient = CalculatorServiceGrpc.newStub(channel);
//
//        CountDownLatch latch = new CountDownLatch(1);
//
//        StreamObserver<FindMaximumRequest> requestStreamObserver = asyncClient.findMaximum(new StreamObserver<FindMaximumResponse>() {
//            @Override
//            public void onNext(FindMaximumResponse value) {
//                System.out.println("Current maximum number: " + value.getMax());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                latch.countDown();
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Server is done");
//                latch.countDown();
//            }
//        });
//
//        Arrays.asList(3, 5, 13, 8, 9, 12, 20).forEach(
//                number -> {
//                    System.out.println("Sending number: " + number);
//                    requestStreamObserver.onNext(FindMaximumRequest.newBuilder().setNumber(number).build());
//                    try {
//                        Thread.sleep(300);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//        );
//
//        requestStreamObserver.onCompleted();
//
//        try {
//            latch.await(3, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        // Error Handling
//        int number = -4324;
//
//        SquareRootRequest squareRootRequest = SquareRootRequest.newBuilder().setNumber(number).build();
//        try {
//            SquareRootResponse squareRootResponse = calculatorClient.squareRoot(squareRootRequest);
//            System.out.println(squareRootResponse.getNumberRoot());
//        } catch (StatusRuntimeException e) {
//            e.printStackTrace();
//        }


        channel.shutdown();
    }
}
