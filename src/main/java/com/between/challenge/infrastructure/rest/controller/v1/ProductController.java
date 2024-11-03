package com.between.challenge.infrastructure.rest.controller.v1;

import com.between.challenge.application.dto.PriceDTO;
import com.between.challenge.application.exception.PricesException;
import com.between.challenge.application.services.IProductService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping (value = "/v1")
@AllArgsConstructor
public class ProductController {

    private IProductService productService;

    @GetMapping(path = "/{country}/products/{product_id}/brand/{brand_id}")
    ResponseEntity<PriceDTO> getPriceByProduct(
            @PathVariable("country") String country,
            @PathVariable("product_id") Integer productId,
            @PathVariable("brand_id") Integer companyId,
            @PathParam("date") String date) throws PricesException, Exception {

        return ResponseEntity.ok( productService.getPriceByProduct(country, productId, companyId, date) );
    }
}
