package com.mycompany.property_management.controller;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.register(userDTO);
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        return response;
    }

    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.login(userDTO.getOwnerEmail(), userDTO.getPassword());
        ResponseEntity<UserDTO> response = new ResponseEntity<>(userDTO, HttpStatus.OK);
        return response;
    }
}
