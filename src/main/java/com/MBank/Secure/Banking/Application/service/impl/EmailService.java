package com.MBank.Secure.Banking.Application.service.impl;

import com.MBank.Secure.Banking.Application.dto.EmailDetails;

public interface EmailService {

    public void sendEmailAlert(EmailDetails emailDetails);
}
