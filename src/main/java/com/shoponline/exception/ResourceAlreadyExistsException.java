package com.shoponline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by dami on 2016-12-24.
 */
public final class ResourceAlreadyExistsException extends BaseException {

    private String message;

    public ResourceAlreadyExistsException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public ResponseEntity<String> getResponse() {
        return new ResponseEntity(message, HttpStatus.CONFLICT);
    }
}
