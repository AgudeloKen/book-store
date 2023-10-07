package com.ken.bookstore.services.auth;

import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.AnErrorHasOccurredException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.LoginRequest;
import com.ken.bookstore.requests.RegisterRequest;
import com.ken.bookstore.responses.TokenResponse;

public interface IAuthService {

    TokenResponse login(LoginRequest request) throws NotFoundException;

    UserDTO register(RegisterRequest request) throws AlreadyExistsException, AnErrorHasOccurredException;
}
