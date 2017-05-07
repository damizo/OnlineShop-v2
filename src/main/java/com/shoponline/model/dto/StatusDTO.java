package com.shoponline.model.dto;

import com.shoponline.model.enums.ResponseStatusType;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Damian on 2017-05-01.
 */
@Getter
@Setter
public class StatusDTO {
    private String message;
    private ResponseStatusType statusType;

    private StatusDTO(){

    }

    public static final StatusDTO createStatusWithMessage(String message){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setMessage(message);
        statusDTO.setStatusType(ResponseStatusType.INFO);
        return statusDTO;
    }

    public static final StatusDTO createStatusWithMessageAndStatus(String message, ResponseStatusType statusType){
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setMessage(message);
        statusDTO.setStatusType(statusType);
        return statusDTO;
    }

}
