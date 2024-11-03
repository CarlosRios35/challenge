package com.between.challenge.application.service;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.application.exception.PricesException;
import com.between.challenge.application.mapper.PriceMapper;
import com.between.challenge.application.services.impl.ProductServiceImpl;
import com.between.challenge.domain.model.Company;
import com.between.challenge.domain.model.Prices;
import com.between.challenge.infrastructure.database.repository.PricesRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PriceMapper priceMapper;

    @SneakyThrows
    private Optional<Prices> getMockPrices() {
        Prices prices =  new Prices();
        Company company = new Company();
        BigDecimal price = new BigDecimal("35.55");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        Date parsedStartDate = dateFormat.parse("2020-06-14-10.00.00");
        Date parsedEndDate = dateFormat.parse("2020-06-16-15.00.00");

        Timestamp startDate = new Timestamp(parsedStartDate.getTime());
        Timestamp endDate = new Timestamp(parsedEndDate.getTime());

        company.setId(1);
        prices.setId(1);
        prices.setProductId(35455);
        prices.setCompany(company);
        prices.setPrice(price);
        prices.setPriority(0);
        prices.setCurrency("EUR");
        prices.setStartDate(startDate);
        prices.setEndDate(endDate);

        return Optional.of(prices);
    }

    private PriceDTO getMockPriceDTO() {
        PriceDTO priceDTO = new PriceDTO();
        priceDTO.setBrandId(1);
        priceDTO.setProductId(35455);
        priceDTO.setPrice(BigDecimal.valueOf(35.55));

        return priceDTO;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * se valida el caso de busqueda exitoso,
     * por lo que se valida el precio de salida del producto
     */
    @Test
    @SneakyThrows
    void getPriceByProductSuccessTest(){
        Mockito.when(pricesRepository.findByBrandIdAndProductIdAndDateAndHigherPriority(anyInt(), anyInt(), Mockito.any(Date.class)))
                .thenReturn(getMockPrices());

        Mockito.when(priceMapper.mapPriceToPriceDTO(getMockPrices().get()))
                .thenReturn(getMockPriceDTO());

        PriceDTO result = productService.getPriceByProduct("es", 35455, 1, "2020-06-14-10.00.00");

        Assertions.assertNotNull(result);
        Mockito.verify(pricesRepository).findByBrandIdAndProductIdAndDateAndHigherPriority(anyInt(), anyInt(), Mockito.any(Date.class));
        Assertions.assertEquals(getMockPrices().get().getPrice(), result.getPrice());
    }

    /**
     * se valida el escenario que la consulta no retorna resultados
     */
    @Test
    void getPriceByProductResultNotFoundTest(){
        PricesException exception = Assertions.assertThrows(PricesException.class, () -> {
            productService.getPriceByProduct("es", 35455, 1, "2020-06-14-15.00.00");
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
    }

    /**
     * se valida el escenario que la fecha no se envia en el formato requerido
     */
    @Test
    void getPriceByProductResultDateNotFoundTest(){
        ParseException exception = Assertions.assertThrows(ParseException.class, () -> {
            productService.getPriceByProduct("es", 35455, 1, "2020-06-14");
        });

        Assertions.assertNotNull(exception);
    }
}
