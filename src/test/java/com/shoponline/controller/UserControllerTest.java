package com.shoponline.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.entity.User;
import com.shoponline.model.enums.UserRole;
import com.shoponline.repository.UserRepository;
import com.shoponline.service.UserService;
import com.shoponline.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Damian on 2017-01-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends BaseControllerTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, messageService);
    }

    @Test
    public void test_user_authorization_success(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Customer customer = new Customer();
        customer.setUserName(userCredentialsDTO.getUserName());
        customer.setPassword(userCredentialsDTO.getPassword());

        given(this.userService.create(userCredentialsDTO)).willReturn(userCredentialsDTO);
        given(this.userService.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);
        
        Assert.assertEquals(this.userService.isAuthorized(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = NotAuthorizedException.class)
    public void test_user_authorization_failure_not_authorized() throws Exception {
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Customer customer = new Customer();
        customer.setUserName(userCredentialsDTO.getUserName());
        customer.setPassword(userCredentialsDTO.getPassword());

        given(this.userService.isAuthorized(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userService.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);

        Assert.assertEquals(this.userService.isAuthorized(userCredentialsDTO), userCredentialsDTO);
    }

    @Test
    public void test_create_user_success() throws Exception {
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Customer customer = new Customer();
        customer.setUserName(userCredentialsDTO.getUserName());
        customer.setPassword(userCredentialsDTO.getPassword());

        given(this.userService.create(userCredentialsDTO)).willReturn(userCredentialsDTO);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = ResourceAlreadyExistsException.class)
    public void test_create_user_failure_already_exsists(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Customer customer = new Customer();
        customer.setUserName(userCredentialsDTO.getUserName());
        customer.setPassword(userCredentialsDTO.getPassword());

        given(this.userService.create(userCredentialsDTO)).willReturn(userCredentialsDTO);
        given(this.userService.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
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
