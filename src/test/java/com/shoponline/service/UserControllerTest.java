package com.shoponline.service;

import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.enums.UserRole;
import com.shoponline.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by Damian on 2017-01-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest extends BaseServiceTest {

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
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);

        Assert.assertEquals(this.userService.isAuthorized(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = NotAuthorizedException.class)
    public void test_user_authorization_failure_not_authorized() throws Exception {
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();
        Customer customer = new Customer();
        customer.setUserName(userCredentialsDTO.getUserName());
        customer.setPassword(userCredentialsDTO.getPassword());

        given(this.userService.isAuthorized(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);

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
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(customer);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = NotAuthorizedException.class)
    public void test_create_super_user_failure(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO(UserRole.SUPER_USER, null);
        SuperUser superUser = createSuperUser(userCredentialsDTO);

        given(this.userService.create(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(superUser);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = NotAuthorizedException.class)
    public void test_create_super_user_failure_wrong_key(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO(UserRole.SUPER_USER, "xXa1DSd15");
        SuperUser superUser = createSuperUser(userCredentialsDTO);

        given(this.userService.create(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword())).willReturn(superUser);
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


    private UserCredentialsDTO createRandomUserCredentialsDTO(UserRole userRole, String superUserKey) {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUserName("Test");
        userCredentialsDTO.setPassword("GGGaaa@@4");
        userCredentialsDTO.setUserRole(userRole);
        userCredentialsDTO.setSuperUserKey(superUserKey);
        return userCredentialsDTO;
    }

}
