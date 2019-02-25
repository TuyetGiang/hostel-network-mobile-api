package com.giang.rest_api.impl;

import com.giang.rest_api.UserApi;
import com.giang.service.UserService;
import com.giang.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<UserDTO> createNewUser(UserDTO newUser) {
        return ResponseEntity.ok(new UserDTO());
    }

    @Override
    public ResponseEntity<UserDTO> updateInformation(Integer id, String key, Object value) {
        return ResponseEntity.ok(new UserDTO());
    }
}
