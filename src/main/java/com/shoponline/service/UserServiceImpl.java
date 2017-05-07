package com.shoponline.service;

import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.exception.ResourceAlreadyExistsException;
import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.SuperUser;
import com.shoponline.model.entity.User;
import com.shoponline.repository.UserRepository;
import com.shoponline.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by dami on 2016-12-24.
 */
@Transactional
@Service
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository<User> userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final MessageService messageService) {
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
        User user = userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), CryptoUtils.SHA256(userCredentialsDTO.getPassword()));

        if (user != null) {
            return userCredentialsDTO;
        }

        throw new NotAuthorizedException(getMessage("userDoesNotExist"));
    }

    @Override
    public UserCredentialsDTO create(UserCredentialsDTO userCredentialsDTO) {

        User user = userRepository.findByUserNameAndPassword(userCredentialsDTO.getEmail(), CryptoUtils.SHA256(userCredentialsDTO.getPassword()));

        if (user != null) {
            throw new ResourceAlreadyExistsException(getMessage("userAlreadyExists"));
        }

        user = prepareUser(userCredentialsDTO);
        user.setPassword(CryptoUtils.SHA256(userCredentialsDTO.getPassword()));
        user.setUserName(userCredentialsDTO.getEmail());
        userRepository.save(user);

        return userCredentialsDTO;
    }

    private User prepareUser(UserCredentialsDTO userCredentialsDTO) {
        User user = null;
        switch (userCredentialsDTO.getUserRole()) {
            case SUPER_USER:
                if (hasProperSecretKey(userCredentialsDTO)) {
                    throw new NotAuthorizedException(getMessage("notAuthorized"));
                }
                user = new SuperUser();
            case CUSTOMER:
                user = new Customer();
        }

        return user;
    }

    private boolean hasProperSecretKey(UserCredentialsDTO userCredentialsDTO) {
        return userCredentialsDTO.getSuperUserKey() == null || !userCredentialsDTO.getSuperUserKey().equals(CryptoUtils.SUPER_USER_KEY);
    }


}
