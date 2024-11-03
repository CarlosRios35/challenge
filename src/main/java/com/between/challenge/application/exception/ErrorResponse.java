package com.between.challenge.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private Integer code;
    private String message;
    private HttpStatusCode status;
}
