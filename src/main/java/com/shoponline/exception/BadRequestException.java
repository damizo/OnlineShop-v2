package com.shoponline.exception;

import com.shoponline.model.dto.StatusDTO;
import com.shoponline.model.enums.ResponseStatusType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Damian on 2017-04-08.
 */
public final class BadRequestException extends BaseException {

    private String message;

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public ResponseEntity<StatusDTO> getResponse() {
        return new ResponseEntity(StatusDTO.createStatusWithMessageAndStatus(message, ResponseStatusType.ERROR), HttpStatus.BAD_REQUEST);
    }
}
