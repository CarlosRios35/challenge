package com.between.challenge.application.mapper;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.domain.model.Prices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;

@ExtendWith(MockitoExtension.class)
public class PriceMapperTest {

    @InjectMocks
    private PriceMapper priceMapper;

    @Test
    public void validateMap(){
        Prices prices = new Prices();
        prices.setPrice(BigDecimal.TEN);
        prices.setProductId(1);
        prices.setStartDate(new Timestamp(System.currentTimeMillis()));
        prices.setEndDate(new Timestamp(System.currentTimeMillis()));

        PriceDTO priceDTO = priceMapper.mapPriceToPriceDTO(prices);

        Assertions.assertNotNull(priceDTO);
        Assertions.assertEquals(prices.getPrice(), priceDTO.getPrice());
        Assertions.assertEquals(prices.getProductId(), priceDTO.getProductId());
        Assertions.assertNotNull(priceDTO.getStartDate());
        Assertions.assertNotNull(priceDTO.getFinalDate());
    }
}
