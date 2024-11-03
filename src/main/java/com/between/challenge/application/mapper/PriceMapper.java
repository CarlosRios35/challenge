package com.between.challenge.application.mapper;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.domain.model.Prices;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@NoArgsConstructor
public class PriceMapper {

    public PriceDTO mapPriceToPriceDTO(Prices prices) {
        PriceDTO priceDTO = new PriceDTO();

        if(prices != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

            if (prices.getPrice() !=  null)
                priceDTO.setPrice(prices.getPrice());
            if (prices.getProductId() != null)
                priceDTO.setProductId(prices.getProductId());
            if(prices.getStartDate() != null )
                priceDTO.setStartDate(formato.format(prices.getStartDate()));
            if (prices.getEndDate() != null)
                priceDTO.setFinalDate(formato.format(prices.getEndDate()));
            if(prices.getCompany() != null && prices.getCompany().getId() != null) {
                priceDTO.setBrandId(prices.getCompany().getId());
            }
        }

        return priceDTO;
    }
}
