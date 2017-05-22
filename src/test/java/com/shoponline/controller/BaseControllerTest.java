package com.shoponline.controller;

import com.google.gson.Gson;
import com.shoponline.repository.ProductRepository;
import com.shoponline.repository.UserRepository;
import com.shoponline.service.MessageService;
import com.shoponline.service.ProductService;
import com.shoponline.service.UserService;
import org.junit.Ignore;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * Created by Damian on 2017-04-09.
 */
@Ignore
public class BaseControllerTest {

    @MockBean
    protected ProductService productService;

    @MockBean
    protected UserService userService;

    @MockBean
    protected UserRepository userRepository;

    @MockBean
    protected ProductRepository productRepository;

    protected static Gson gson = null;

    protected static Gson getGsonInstance(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }
}
