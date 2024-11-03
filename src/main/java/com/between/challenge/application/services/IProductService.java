package com.between.challenge.application.services;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.application.exception.PricesException;

public interface IProductService {

    PriceDTO getPriceByProduct(String country, Integer productId, Integer companyId, String date) throws PricesException, Exception;
}
