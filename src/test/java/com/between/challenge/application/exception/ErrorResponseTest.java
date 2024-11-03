package com.between.challenge.application.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ErrorResponseTest {

    @Test
    public void errorResponseTest(){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(500);
        errorResponse.setMessage("ERROR");
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);

        Assertions.assertNotNull(errorResponse);
    }
}
