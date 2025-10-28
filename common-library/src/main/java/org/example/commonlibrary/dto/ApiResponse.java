package org.example.commonlibrary.dto;


import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder

public class ApiResponse<T> {
   private String message;
   private  int status;
   private T data;

}
