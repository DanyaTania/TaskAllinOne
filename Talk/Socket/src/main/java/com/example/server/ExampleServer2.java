package com.example.server;



import com.example.dto.SimpleDto2;
import com.example.service.ExampleHandlerService2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class ExampleServer2 {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;


    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            // In ExampleHandlerService should be your logic
            SimpleDto2 dto = (SimpleDto2) in.readObject();
            log.info("Message from client: {}", dto.getText());
            ExampleHandlerService2.handle(dto);
            log.info("Message from handle service: {}", dto.getText());
            out.writeObject(dto);
        } catch (IOException | ClassNotFoundException e) {
            log.error("Server can't start for reason: {}", e.getMessage());
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            log.error("Server can't stop for reason: {}", e.getMessage());
        }
    }
}
