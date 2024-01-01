package com.MBank.Secure.Banking.Application.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_EXISTS_CODE="001";
    public static final String ACCOUNT_EXISTS_MESSAGE="This user already has an account ";
    public static final String ACCOUNT_CREATION_CODE="002";
    public static final String ACCOUNT_CREATION_MESSAGE="Created User ";

    public static String generateAccountNumber()
    {
        /*
         * 2023 + randomSixDigits
         */

        Year currentYear= Year.now();

        int min =100000;
        int max =999999;

        int randNumber= (int) Math.floor(Math.random() * (max - min +1) + min);

        String year =String.valueOf(currentYear);
        String randomNumber= String.valueOf(randNumber);
        StringBuilder accountNumber= new StringBuilder();

        return accountNumber.append(year).append(randomNumber).toString();
    }
}
