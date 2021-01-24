package com.example.service;

import com.example.dto.SimpleDto2;

import static org.apache.commons.lang3.StringUtils.reverse;

public class ExampleHandlerService2 {

    public static void handle(SimpleDto2 dto) {
        dto.setText(reverse(dto.getText()));
    }
}
