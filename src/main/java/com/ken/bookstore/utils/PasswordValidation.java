package com.ken.bookstore.utils;

import org.springframework.stereotype.Service;

@Service
public class PasswordValidation {

    private static final String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_*#$=+{}|;:<>,.])[a-zA-Z\\d_*#]{8,16}$";

    public boolean validate(String password){
        return password.matches(pattern);
    }
}
