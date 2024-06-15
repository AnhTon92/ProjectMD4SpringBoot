package com.ra.projectspringboot.advice;

import com.ra.projectspringboot.constants.EHttpStatus;
import com.ra.projectspringboot.dto.ResponseWrapper;
import com.ra.projectspringboot.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationHandler {

    /**
     * @param ex MethodArgumentNotValidException
     *           handle valid data in request (validation)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity
                .badRequest()
                .body(
                        ResponseWrapper.builder()
                                .status(EHttpStatus.FAILED)
                                .code(400)
                                .data(errors)
                                .build()
                );
    }

    /**
     * @param ex CustomException
     *           handle exception is CustomException have httpStatus and message
     */

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(
                ResponseWrapper.builder()
                        .status(EHttpStatus.FAILED)
                        .code(ex.getHttpStatus().value())
                        .data(ex.getMessage())
                        .build(),
                ex.getHttpStatus()
        );
    }

}
