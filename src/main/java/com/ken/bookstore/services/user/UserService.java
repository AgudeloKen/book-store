package com.ken.bookstore.services.user;

import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.models.User;
import com.ken.bookstore.repositories.UserRepository;
import com.ken.bookstore.requests.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    @Override
    public UserDTO save(UserRequest user) throws AlreadyExistsException {
        if(!userRepository.existsByEmail(user.getEmail())){
            throw new AlreadyExistsException("User Already Exists");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCountry(user.getCountry());
        newUser.setRole(user.getRole());
        return new UserDTO(userRepository.save(newUser));
    }

    @Override
    public UserDTO findById(Long id) throws NotFoundException {
        if(!userRepository.existsById(id)){
            throw new NotFoundException("User not found");
        }
        return new UserDTO(userRepository.getReferenceById(id));
    }

    @Override
    public UserDTO update(Long id, UserRequest user) throws NotFoundException {
        if(!userRepository.existsById(id)){
            throw new NotFoundException("User not found");
        }

        User newUser = userRepository.getReferenceById(id);
        newUser.setName(user.getName());
        newUser.setCountry(user.getCountry());

        return new UserDTO(userRepository.save(newUser));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!userRepository.existsById(id)){
            throw new NotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
