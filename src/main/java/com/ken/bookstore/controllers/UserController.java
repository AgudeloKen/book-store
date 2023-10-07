package com.ken.bookstore.controllers;

import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.UserRequest;
import com.ken.bookstore.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid UserRequest userRequest) throws AlreadyExistsException {
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest) throws NotFoundException {
        return ResponseEntity.ok(userService.update(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
