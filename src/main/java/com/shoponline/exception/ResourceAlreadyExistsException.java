package com.shoponline.exception;

import com.shoponline.model.dto.StatusDTO;
import com.shoponline.model.enums.ResponseStatusType;
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
    public ResponseEntity<StatusDTO> getResponse() {
        return new ResponseEntity(StatusDTO.createStatusWithMessageAndStatus(message, ResponseStatusType.ERROR), HttpStatus.CONFLICT);
    }
}
