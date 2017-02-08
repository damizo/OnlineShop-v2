package com.shoponline.service;

import com.shoponline.aspect.BaseAspect;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.entity.User;
import com.shoponline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by dami on 2016-12-24.
 */
@Transactional
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MessageService messageService) {
        this.userRepository = userRepository;
        this.messageService = messageService;
    }

    @Override
    public User createCustomer(Customer customer) {
        if (userRepository.findByUserName(customer.getUserName()) != null) {
            throw new ResourceAlreadyExistsException(getMessage("resourceAlreadyExists"));
        }
       return userRepository.save(customer);
    }

    @Override
    public UserCredentialsDTO isAuthorized(UserCredentialsDTO userCredentialsDTO) {
        User user = userRepository.findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword());

        if (user != null) {
            return userCredentialsDTO;
        }

        throw new NotAuthorizedException(getMessage("notAuthorized"));
    }

    @Override
    public UserCredentialsDTO create(UserCredentialsDTO userCredentialsDTO) {

        User user = findByUserNameAndPassword(userCredentialsDTO.getUserName(), userCredentialsDTO.getPassword());

        if(user != null){
            throw new ResourceAlreadyExistsException(getMessage("userAlreadyExists"));
        }

       switch (userCredentialsDTO.getUserRole()) {
            case SUPER_USER:
                user = new SuperUser();
                break;
            case CUSTOMER:
                user = new Customer();
                break;
        }

        user.setPassword(userCredentialsDTO.getPassword());
        user.setUserName(userCredentialsDTO.getUserName());

        saveUser(user);

        return userCredentialsDTO;
    }

    @Override
    public User findByUserNameAndPassword(String userName, String password) {
        return userRepository.findByUserNameAndPassword(userName, password);
    }


    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
}
