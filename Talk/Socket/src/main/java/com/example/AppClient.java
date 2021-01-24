package com.example;

import com.example.client.ExampleClient;

//import com.example.dto.SimpleDto;
import com.example.dto.SimpleDto2;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.io.FileUtils.readFileToString;

@Slf4j
public class AppClient {

    private static final String FILE_PATH = "E:\\new_doc\\Java\\Talk\\Socket\\build\\resources\\main\\resource.txt";
    private static final String CLIENT_IP = "127.0.0.1";
    private static final Integer SERVER_PORT = 8888;


    public static void main(String[] args) throws IOException {
        log.info("Client starting...");
        ExampleClient client = new ExampleClient();
        client.startConnection(CLIENT_IP, SERVER_PORT);
        SimpleDto2 request = deserializationFromFile();
        SimpleDto2 response = client.sendMessage(request);
        log.info("Result is: {}", response.getText());
    }

    private static SimpleDto2 deserializationFromFile() {
        return getSimpleDto2(FILE_PATH);
    }

    public static SimpleDto2 getSimpleDto2(String filePath) {
        try {
            ClassLoader classLoader = AppClient.class.getClassLoader();


            String resource = requireNonNull(classLoader.getResource(filePath)).getFile();
            String text = readFileToString(new File(resource), "UTF_8");
            return new SimpleDto2(text);
        } catch (IOException ex) {
            log.error("Can't read resource for reason: {}", ex.getMessage());
            throw new RuntimeException("Can't read resource.");
        }
    }

}
