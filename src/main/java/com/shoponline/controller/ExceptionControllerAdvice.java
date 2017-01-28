package com.shoponline.controller;

import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.xml.ws.Response;

/**
 * Created by dami on 2016-12-24.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResponseEntity handleNotAuthorized(NotAuthorizedException e) {
        return e.getResponse();
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ResponseEntity handleResourceAlreadyExists(ResourceAlreadyExistsException e) {
        return e.getResponse();
    }

}
