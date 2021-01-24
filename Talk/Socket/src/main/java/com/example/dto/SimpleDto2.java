package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class SimpleDto2 implements Serializable {

    private String text;


//SimpleDto2(String text){
  //  this.text=text;
//}

}
