package com.shoponline.controller;

import com.google.gson.Gson;
import com.shoponline.service.MessageService;
import com.shoponline.service.MessageServiceImpl;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Damian on 2017-01-21.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseControllerTest {

    @Mock
    protected MessageService messageService;

    protected static Gson gson = null;

    protected static Gson getGsonInstance(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

}
