package com.MBank.Secure.Banking.Application.service.impl;

import com.MBank.Secure.Banking.Application.dto.AccountInfo;
import com.MBank.Secure.Banking.Application.dto.BankResponse;
import com.MBank.Secure.Banking.Application.dto.EmailDetails;
import com.MBank.Secure.Banking.Application.dto.UserRequest;
import com.MBank.Secure.Banking.Application.entity.User;
import com.MBank.Secure.Banking.Application.repository.UserRepository;
import com.MBank.Secure.Banking.Application.utils.AccountUtils;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
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
        EmailDetails details= EmailDetails.builder()
                .messageBody("Congratulations! Your Account Has Been Successfully Created.\n Your Account Details :"+
                        "Account Name: "+ saveUser.getFirstName()+" " + saveUser.getLastName()
                        )
                .recipient(userRequest.getEmail())
                .subject("ACCOUNT CREATION")
                .build();
        emailService.sendEmailAlert(details);
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
