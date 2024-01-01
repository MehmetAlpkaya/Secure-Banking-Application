package com.MBank.Secure.Banking.Application.controller;

import com.MBank.Secure.Banking.Application.dto.BankResponse;
import com.MBank.Secure.Banking.Application.dto.UserRequest;
import com.MBank.Secure.Banking.Application.service.impl.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
}
