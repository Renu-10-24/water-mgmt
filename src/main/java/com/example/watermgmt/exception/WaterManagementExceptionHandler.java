package com.example.watermgmt.exception;

import com.example.watermgmt.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class WaterManagementExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler(value= ConstraintViolationException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleConstraintViolation(ConstraintViolationException cve){
        return ErrorResponse.builder().message(cve.getMessage()).build();
    }

//    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException cve){
        return ErrorResponse.builder().message(cve.getMessage()).build();
    }
    @ExceptionHandler(value= Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleAnyException(Exception e){
        return ErrorResponse.builder().message(e.getMessage()).build();
    }

}
