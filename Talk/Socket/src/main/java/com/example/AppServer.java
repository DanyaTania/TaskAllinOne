package com.example;

import com.example.server.ExampleServer2;
import com.example.server.ExampleServer2;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AppServer {

    private static final Integer SERVER_PORT = 8888;

    public static void main(String[] args) throws IOException {
        log.info("Server starting...");
        ExampleServer2 server = new ExampleServer2();
        server.start(SERVER_PORT);
    }

}

