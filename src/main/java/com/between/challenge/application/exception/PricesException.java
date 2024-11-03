package com.between.challenge.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricesException extends Throwable {
    private Integer code;
    private String message;
    private HttpStatus status;
}
