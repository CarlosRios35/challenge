package com.between.challenge.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {
    private Integer brandId;
    private Integer productId;
    private BigDecimal price;
    private String startDate;
    private String finalDate;
}
