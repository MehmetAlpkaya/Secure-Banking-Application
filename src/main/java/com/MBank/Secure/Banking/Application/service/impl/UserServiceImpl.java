package com.MBank.Secure.Banking.Application.service.impl;

import com.MBank.Secure.Banking.Application.dto.AccountInfo;
import com.MBank.Secure.Banking.Application.dto.BankResponse;
import com.MBank.Secure.Banking.Application.dto.UserRequest;
import com.MBank.Secure.Banking.Application.entity.User;
import com.MBank.Secure.Banking.Application.repository.UserRepository;
import com.MBank.Secure.Banking.Application.utils.AccountUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        if(userRepository.existsByEmail(userRequest.getEmail())) {

            BankResponse response=BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
            return response;
        }
        User newUser=User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                .status("ACTIVE")
                .build();

        User saveUser=userRepository.save(newUser);
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(newUser.getFirstName() + " "+ newUser.getLastName())
                        .accountBalance(newUser.getAccountBalance())
                        .accountNumber(newUser.getAccountNumber())
                        .build())
                .build();
    }
}
