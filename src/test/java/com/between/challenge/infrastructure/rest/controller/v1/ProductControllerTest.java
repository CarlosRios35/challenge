package com.between.challenge.infrastructure.rest.controller.v1;

import com.between.challenge.ChallengeApplication;
import com.between.challenge.application.services.IProductService;
import com.between.challenge.infrastructure.database.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ChallengeApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductService productService;

    @Autowired
    private PricesRepository pricesRepository;

    //parametros utilizados para la prueba
    private String prueba1= "petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String prueba2= "petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String prueba3= "petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)";
    private String prueba4= "petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)";
    private String prueba5= "petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)";

    @Test
    void getPriceByProductTest1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void getPriceByProductTest2() throws Exception {
         mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-16.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price",is(25.45)));
    }

    @Test
    void getPriceByProductTest3() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-14-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price", is(35.50)));
    }

    @Test
    void getPriceByProductTest4() throws Exception {
       mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-15-10.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price", is(30.50)));
    }

    @Test
    void getPriceByProductTest5() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                .queryParam("date", "2020-06-16-21.00.00")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.price", is(38.95)));
    }

    @Test
    void getPriceByProductTest6() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/{country}/products/{product_id}/brand/{company_id}","es",35455,1)
                        .queryParam("date", "2024-11-03-21.00.00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
