package com.MBank.Secure.Banking.Application.service.impl;

import com.MBank.Secure.Banking.Application.dto.BankResponse;
import com.MBank.Secure.Banking.Application.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
}
