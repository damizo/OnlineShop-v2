package com.shoponline.exception;

import com.shoponline.model.dto.StatusDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by dami on 2016-12-24.
 */
public abstract class BaseException extends RuntimeException {

    public BaseException(String message){
        super(message);
    }

    public abstract ResponseEntity<StatusDTO> getResponse();
}
