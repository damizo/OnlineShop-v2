package com.shoponline.controller;

import com.shoponline.model.dto.UserCredentialsDTO;
import com.shoponline.model.entity.Customer;
import com.shoponline.model.entity.User;
import com.shoponline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by dami on 2016-12-11.
 */
@RestController
@RequestMapping(value = "/user")
public class  UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserCredentialsDTO> createUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(userService.create(userCredentialsDTO));
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public ResponseEntity<UserCredentialsDTO> authorizeUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(userService.isAuthorized(userCredentialsDTO));
    }

}
