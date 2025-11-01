package org.example.commonlibrary.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
   private final String message;
   private final boolean success;
   private  T data;


}
