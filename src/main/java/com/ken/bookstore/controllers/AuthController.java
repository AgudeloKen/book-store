package com.ken.bookstore.controllers;


import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.AnErrorHasOccurredException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.LoginRequest;
import com.ken.bookstore.requests.RegisterRequest;
import com.ken.bookstore.responses.TokenResponse;
import com.ken.bookstore.services.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid LoginRequest request) throws NotFoundException {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid RegisterRequest request) throws AlreadyExistsException, AnErrorHasOccurredException {
        return ResponseEntity.ok(authService.register(request));
    }
}
