package com.shoponline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by dami on 2016-12-24.
 */
@Service
public class BaseService {

    @Autowired
    protected MessageService messageService;

    public String getMessage(String id){
        return messageService.getMessage(id);
    }

}
