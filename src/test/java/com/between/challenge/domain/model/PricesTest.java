package com.between.challenge.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class PricesTest {

    @Test
    public void priceTest(){
        Prices prices = new Prices();
        prices.setPrice(BigDecimal.ZERO);
        prices.setProductId(1);
        prices.setPriceList(1);
        prices.setPriority(0);
        prices.setCurrency("EUR");

        Company company = new Company();
        company.setId(1);
        company.setNameDescription("SARA");
        prices.setCompany(company);
        Assertions.assertNotNull(prices);
    }
}
