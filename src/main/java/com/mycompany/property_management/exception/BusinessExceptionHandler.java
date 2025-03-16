package com.mycompany.property_management.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BusinessExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(BusinessExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex) {
        for (ErrorModel em : bex.getErrors()) {
            logger.info("Inside field validation  level - info : {} - {} ",em.getCode(), em.getMessage());
            logger.debug("Inside field validation level - debug: {} - {} ",em.getCode(), em.getMessage());
            logger.trace("Inside field validation level - trace : {} - {} ",em.getCode(), em.getMessage());
            logger.warn("Inside field validation level - warn : {} - {} ",em.getCode(), em.getMessage());
            logger.error("Inside field validation level - error : {} - {} ",em.getCode(), em.getMessage());

        }
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ErrorModel> errorModels = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            logger.info("Inside field validation : {} - {} ",fieldError.getField(),fieldError.getDefaultMessage());
            logger.debug("Inside field validation : {} - {} ",fieldError.getField(),fieldError.getDefaultMessage());
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(fieldError.getField());
            errorModel.setMessage(fieldError.getDefaultMessage());
            errorModels.add(errorModel);
        }
        return new ResponseEntity<List<ErrorModel>>(errorModels, HttpStatus.BAD_REQUEST);
    }
}
