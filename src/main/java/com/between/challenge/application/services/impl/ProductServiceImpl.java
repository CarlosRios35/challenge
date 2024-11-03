package com.between.challenge.application.services.impl;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.application.services.IProductService;
import com.between.challenge.commons.constants.Constants;
import com.between.challenge.application.exception.PricesException;
import com.between.challenge.application.mapper.PriceMapper;
import com.between.challenge.infrastructure.database.repository.PricesRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final PricesRepository pricesRepository;

    private final PriceMapper priceMapper;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public PriceDTO getPriceByProduct(String country, Integer productId, Integer companyId, String date) throws PricesException, Exception {
        try {
            LOGGER.info("[ProductServiceImpl]:getPriceByProduct - inicia consulta productId:[{}] brandId:[{}] Fecha:[{}]",
                    productId, companyId, date);

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

            Date fecha = formato.parse(date);

            var prices = pricesRepository.findByBrandIdAndProductIdAndDateAndHigherPriority(companyId, productId, fecha);
            if (prices.isPresent()){
                return priceMapper.mapPriceToPriceDTO(prices.get());
            }else{
                throw new PricesException(HttpStatus.BAD_REQUEST.value(), Constants.priceNotFound, HttpStatus.BAD_REQUEST);
            }
        } catch (PricesException | Exception ex){
            LOGGER.error("[ProductServiceImpl]:getPriceByProduct - fallo la consulta productId:[{}] brandId:[{}] Fecha:[{}] error:[{}]",
                    productId, companyId, date, ex.getMessage());
            throw ex;
        }
    }
}
