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
@CrossOrigin
@RestController
@RequestMapping(value = "/users")
public class  UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserCredentialsDTO> createUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(userService.create(userCredentialsDTO));
    }

    @PostMapping(value = "/authorize")
    public ResponseEntity<UserCredentialsDTO> authorizeUser(@RequestBody UserCredentialsDTO userCredentialsDTO) {
        return ResponseEntity.ok(userService.isAuthorized(userCredentialsDTO));
    }

}
