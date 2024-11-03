package com.between.challenge.application.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class PriceExceptionTest {

    @Test
    public void priceExceptionTest(){
        PricesException pricesException = new PricesException();
        pricesException.setCode(500);
        pricesException.setStatus(HttpStatus.BAD_REQUEST);
        pricesException.setMessage("ERROR");

        Assertions.assertNotNull(pricesException);
    }
}
