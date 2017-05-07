package com.shoponline.service;

import com.shoponline.MockDatabase;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.entity.User;
import com.shoponline.model.enums.UserRole;
import com.shoponline.repository.UserRepository;
import com.shoponline.utils.CryptoUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.given;

/**
 * Created by Damian on 2017-01-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends BaseServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private final User customer = MockDatabase.CUSTOMER_ENTITIES.get(0);

    private final UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository, messageService);
    }

    @Test
    public void shouldCreateCustomer(){
        given(this.userService.create(userCredentialsDTO))
                .willReturn(userCredentialsDTO);


        UserCredentialsDTO actualCredentials = this.userService.create(userCredentialsDTO);



        Assert.assertEquals(userCredentialsDTO, actualCredentials);
    }

    @Test
    public void shouldFindCustomer(){
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), CryptoUtils.SHA256(userCredentialsDTO.getPassword())))
                .willReturn(customer);

        Customer actualCustomer = (Customer) this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), CryptoUtils.SHA256(userCredentialsDTO.getPassword()));

        Assert.assertEquals(customer, actualCustomer);
    }


    @Test
    public void shouldAuthorizeUser(){
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), CryptoUtils.SHA256(userCredentialsDTO.getPassword())))
                .willReturn(customer);
        given(this.userService.isAuthorized(userCredentialsDTO))
                .willReturn(userCredentialsDTO);

        Assert.assertNotNull(this.userService.isAuthorized(userCredentialsDTO));
    }

    @Test(expected = NotAuthorizedException.class)
    public void shouldNotAuthorizeUser() throws Exception {
        given(this.userService.isAuthorized(userCredentialsDTO)).willThrow(NotAuthorizedException.class);

        Assert.assertNotNull(this.userService.isAuthorized(userCredentialsDTO));
    }


    @Test(expected = NotAuthorizedException.class)
    public void shouldNotCreateSuperUser(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO(UserRole.SUPER_USER, null);
        SuperUser superUser = createSuperUser(userCredentialsDTO);

        given(this.userService.create(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword())).willReturn(superUser);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
    }

    @Test(expected = NotAuthorizedException.class)
    public void shouldNotCreateSuperUserWithWrongKey(){
        UserCredentialsDTO userCredentialsDTO = createRandomUserCredentialsDTO(UserRole.SUPER_USER, "xXa1DSd15");
        SuperUser superUser = createSuperUser(userCredentialsDTO);

        given(this.userService.create(userCredentialsDTO)).willThrow(NotAuthorizedException.class);
        given(this.userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), userCredentialsDTO.getPassword())).willReturn(superUser);
        Assert.assertEquals(this.userService.create(userCredentialsDTO), userCredentialsDTO);
    }

    private SuperUser createSuperUser(UserCredentialsDTO userCredentialsDTO) {
        SuperUser user = new SuperUser();
        user.setUserName(userCredentialsDTO.getEmail());
        user.setPassword(userCredentialsDTO.getPassword());
        return user;
    }

    private UserCredentialsDTO createRandomUserCredentialsDTO() {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setEmail("Test");
        userCredentialsDTO.setPassword("GGGaaa@@4");
        userCredentialsDTO.setUserRole(UserRole.CUSTOMER);
        return userCredentialsDTO;
    }


    private UserCredentialsDTO createRandomUserCredentialsDTO(UserRole userRole, String superUserKey) {
        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setEmail("Test");
        userCredentialsDTO.setPassword("GGGaaa@@4");
        userCredentialsDTO.setUserRole(userRole);
        userCredentialsDTO.setSuperUserKey(superUserKey);
        return userCredentialsDTO;
    }

}
