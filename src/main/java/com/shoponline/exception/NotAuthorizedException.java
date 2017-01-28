package com.shoponline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by dami on 2016-12-24.
 */
public final class NotAuthorizedException extends BaseException {

    private String message;

    public NotAuthorizedException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public ResponseEntity<String> getResponse(){
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
