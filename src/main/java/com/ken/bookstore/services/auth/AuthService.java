package com.ken.bookstore.services.auth;

import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.AnErrorHasOccurredException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.models.User;
import com.ken.bookstore.repositories.UserRepository;
import com.ken.bookstore.requests.LoginRequest;
import com.ken.bookstore.requests.RegisterRequest;
import com.ken.bookstore.responses.TokenResponse;
import com.ken.bookstore.security.TokenConfiguration;
import com.ken.bookstore.utils.PasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenConfiguration tokenConfiguration;

    private final PasswordValidation passwordValidation;

    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(LoginRequest request) throws NotFoundException {
        if(!userRepository.existsByEmail(request.getEmail())){
            throw new NotFoundException("No user exists with this email.");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        Authentication userAuth = authenticationManager.authenticate(authentication);

        return new TokenResponse(tokenConfiguration.generateToken((User) userAuth.getPrincipal()));
    }

    @Override
    public UserDTO register(RegisterRequest request) throws AlreadyExistsException, AnErrorHasOccurredException {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AlreadyExistsException("A user with this email already exists.");
        }

        if(!passwordValidation.validate(request.getPassword())){
            throw new AnErrorHasOccurredException("The password must contain 8 to 16 characters, at least one capital letter, one number and one special character.");
        }

        User user = new User();
        user.setName(request.getName());
        user.setCountry(request.getCountry());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return new UserDTO(userRepository.save(user));
    }
}
