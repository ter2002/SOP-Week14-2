package GreetingServer;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GreetingServer {
    public static void main(String[] args) {
        System.out.println("Hello gRPC");
        Server server = ServerBuilder.forPort(50055)
                .addService((BindableService) new GreetServiceImpl())
                .build();

        try {
            server.start();
            System.out.println("Server Started");
        }catch (Exception e){
           e.printStackTrace();
        }

        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {
                    System.out.println("Receive Shutdown Request");
                    server.shutdown();
                    System.out.println("Successfully Shutdown Server");
                }
        ));

        try{
            server.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
