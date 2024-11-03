package com.between.challenge.application.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class PriceDTOTest {

    @Test
    public void priceDTOValidTest(){
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setPrice(BigDecimal.ZERO);
        priceDTO.setBrandId(1);
        priceDTO.setProductId(1);
        priceDTO.setStartDate("2020-06-14-00.00.00");
        priceDTO.setFinalDate("2020-06-14-00.00.00");

        Assertions.assertNotNull(priceDTO);
    }
}
