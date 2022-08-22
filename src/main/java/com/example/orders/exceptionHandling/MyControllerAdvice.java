package com.example.orders.exceptionHandling;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException)
    {
        return  new ResponseEntity<String>("Input field is empty", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElement(NoSuchElementException noSuchElementException)
    {
        return  new ResponseEntity<String>("No value is present in DB,Please check ", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public  ResponseEntity<String> handleEmptyResult(EmptyResultDataAccessException emptyResultDataAccessException)
    {
        return  new ResponseEntity<String>("No such element in DB",HttpStatus.NOT_FOUND);
    }
    @Override
    protected  ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        return new ResponseEntity<Object>("Please change your http method type😀😁",HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException nullPointerException)
    {
        return new ResponseEntity<String>("No specified element in DB",HttpStatus.NOT_FOUND);
    }
   @ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<String> handleJDBCConnectionException(JDBCConnectionException jdbcConnectionException)
   {
       return  new ResponseEntity<String>("Cannot connect to Database",HttpStatus.SERVICE_UNAVAILABLE);
   }

}
