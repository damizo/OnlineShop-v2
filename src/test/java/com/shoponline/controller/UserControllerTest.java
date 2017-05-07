package com.shoponline.controller;

import com.shoponline.MockDatabase;
import com.shoponline.model.dto.UserCredentialsDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by Damian on 2017-05-02.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldRegisterNewUser() throws Exception {
        UserCredentialsDTO potentialNewUser = MockDatabase.USERS_DTO.get(0);

        Mockito.when(this.userService.create(potentialNewUser)).thenReturn(potentialNewUser);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(getGsonInstance()
                        .toJson(potentialNewUser)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().json(getGsonInstance().toJson(potentialNewUser)));
    }

    @Test
    public void shouldAuthorizeUser() throws Exception {

    }
}
