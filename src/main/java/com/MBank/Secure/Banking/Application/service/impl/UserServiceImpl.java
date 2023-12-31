package com.MBank.Secure.Banking.Application.service.impl;

import com.MBank.Secure.Banking.Application.dto.BankResponse;
import com.MBank.Secure.Banking.Application.dto.UserRequest;
import com.MBank.Secure.Banking.Application.entity.User;
import com.MBank.Secure.Banking.Application.repository.UserRepository;
import com.MBank.Secure.Banking.Application.utils.AccountUtils;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        User newUser=User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .StateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        return null;
    }
}
