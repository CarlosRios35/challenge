package com.between.challenge.infrastructure.rest.handler;

import com.between.challenge.application.exception.PricesException;
import com.between.challenge.application.services.IProductService;
import com.between.challenge.commons.constants.Constants;
import com.between.challenge.infrastructure.rest.controller.v1.ProductController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@EnableWebMvc
@AutoConfigureMockMvc
@SpringBootTest(classes = ProductController.class)
public class ApiExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiExceptionHandler apiExceptionHandler;

    @MockBean
    private IProductService productService;

    @Test
    public void handleBusinessExceptionTest() throws Exception, PricesException {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,"s")
                        .queryParam("date", "2020-06-14-00.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void handlePriceExceptionTest() throws Exception, PricesException {

        Mockito.when(productService.getPriceByProduct(anyString(), anyInt(), anyInt(), anyString()))
                .thenThrow(new PricesException(HttpStatus.BAD_GATEWAY.value(), Constants.nullMsgError, HttpStatus.BAD_REQUEST));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,"1")
                        .queryParam("date", "2020-06-14-00.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
