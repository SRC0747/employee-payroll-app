package com.bridgelabz.employeepayrollapp.exception;

import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;

/**
 * Purpose : Handle bothe the vallidation and custom exception if it is occurred
 *
 * @author : Sampriti Roy Chowdhury
 * @version : 0.0.1
 * @since : 15-12-2021
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Purpose : This method is used to handle Custom Exception
     *
     * @param customException defines customized exception
     * @return response message if it is occurred
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ResponseDTO> handleCustomException(CustomException customException){
        return new ResponseEntity<>(new ResponseDTO(customException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    /**
     * Purpose : This method is used to handle Vallidation Exception occurred during the handle of vallidation fields
     *
     * @param validationException defines exception occurred during vallidation
     * @return response message if it is occurred
     */

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(ValidationException validationException){
        return new ResponseEntity<>(new ResponseDTO(validationException.getMessage(), null), HttpStatus.NOT_FOUND);
    }
}
