package com.between.challenge.infrastructure.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.stereotype.Component;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String productId = request.getRequestURI().split("/")[4]; //obtengo el pruductId
        String brandId = request.getRequestURI().split("/")[6]; //obtengo el brandId
        String date = request.getParameter("date");//obtengo el date

        //se validan que se envien en caso contrario retorno un error generico para evitar darle información a un atacante
        if(productId == null || brandId == null || date == null || Integer.parseInt(productId) < 0 || Integer.parseInt(brandId) < 0) {
            throw new IllegalArgumentException("uno o varios parametros tienen un formato invalido, validar los parametros enviados");
        }

        return true;
    }
}
