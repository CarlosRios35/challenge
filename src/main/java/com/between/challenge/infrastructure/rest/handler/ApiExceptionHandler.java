package com.between.challenge.infrastructure.rest.handler;

import com.between.challenge.commons.constants.Constants;
import com.between.challenge.application.exception.ErrorResponse;
import com.between.challenge.application.exception.PricesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.text.ParseException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({ PricesException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handlePriceException(PricesException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), ex.getStatus());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBusinessException(MethodArgumentTypeMismatchException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Constants.nullMsgError, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleParseException(ParseException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Constants.nullMsgError, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleBusinessException(IllegalArgumentException e) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), Constants.nullMsgError, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
