package com.shoponline.controller;

import com.google.gson.Gson;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Damian on 2017-01-21.
 */
@Ignore
@RunWith(SpringRunner.class)
public class BaseControllerTest {

    @Autowired
    protected UserController userController;

    protected static Gson gson = null;

    protected static Gson getGsonInstance(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

    @Test
    public void contextLoads() {
    }
}
