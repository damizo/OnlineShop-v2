package com.shoponline.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.enums.UserRole;
import com.shoponline.repository.UserRepository;
import com.shoponline.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Damian on 2017-01-20.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;


    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    public void test_throw_not_authorized_exception() throws Exception {

        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Gson gson = getGsonInstance();

        given(this.userService.isAuthorized(userCredentialsDTO)).willThrow(NotAuthorizedException.class);

        this.mvc.perform(MockMvcRequestBuilders.get("/user/authorize")
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userCredentialsDTO)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test_register_user_twice() throws Exception {

        Gson gson = getGsonInstance();
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();

        given(this.userService.create(userCredentialsDTO)).willReturn(ResponseEntity.ok(userCredentialsDTO));
        given(this.userService.create(userCredentialsDTO)).willThrow(ResourceAlreadyExistsException.class);

        this.mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userCredentialsDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        this.mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userCredentialsDTO)))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void test_create_user() throws Exception {

        Gson gson = getGsonInstance();
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();

        given(this.userService.create(userCredentialsDTO)).willReturn(ResponseEntity.ok(userCredentialsDTO));

        this.mvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(userCredentialsDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private SuperUser createSuperUser(UserCredentialsDTO userCredentialsDTO) {
        SuperUser user = new SuperUser();
        user.setUserName(userCredentialsDTO.getUserName());
        user.setPassword(userCredentialsDTO.getPassword());
        return user;
    }

    private UserCredentialsDTO createRandomUserCredentialsDTO() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUserName("Test");
        userCredentialsDTO.setPassword("GGGaaa@@4");
        userCredentialsDTO.setUserRole(UserRole.CUSTOMER);
        return userCredentialsDTO;
    }


}
