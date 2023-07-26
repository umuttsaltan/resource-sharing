package com.alperumut.resourcesharing.exceptions;


import com.alperumut.resourcesharing.entities.dtos.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                           WebRequest webRequest) {
        // Create an ErrorDetailsDto object to hold the error response details
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(
                new Date(),                 // timestamp
                exception.getMessage(),     // error message
                webRequest.getDescription(false) // request description
        );

        // Return the error response with HTTP status code 404 NOT FOUND
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceSharingAPIException.class)
    public ResponseEntity<ErrorDetailsDto> handleBlogAPIException(ResourceSharingAPIException exception,
                                                                  WebRequest webRequest) {
        // Create an ErrorDetailsDto object to hold the error response details
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(
                new Date(),                 // timestamp
                exception.getMessage(),     // error message
                webRequest.getDescription(false) // request description
        );

        // Return the error response with HTTP status code 400 BAD REQUEST
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Add more exception handling methods as needed

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> handleGlobalException(Exception exception,
                                                                 WebRequest webRequest) {
        // Create an ErrorDetailsDto object to hold the error response details
        ErrorDetailsDto errorDetails = new ErrorDetailsDto(
                new Date(),                 // timestamp
                exception.getMessage(),     // error message
                webRequest.getDescription(false) // request description
        );

        // Return the error response with HTTP status code 500 INTERNAL SERVER ERROR
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Add more global exception handling methods as needed

}