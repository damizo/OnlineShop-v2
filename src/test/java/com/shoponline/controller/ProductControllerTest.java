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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by Damian on 2017-04-09.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest  extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetAllProducts() throws Exception {
        Mockito.when(this.productService.fetchProducts()).thenReturn(MockDatabase.PRODUCTS_DTO);


        this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getGsonInstance().toJson(MockDatabase.PRODUCTS_DTO)));
    }
}
