package com.ken.bookstore.controllers;

import com.ken.bookstore.dto.AuthorDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.AuthorRequest;
import com.ken.bookstore.services.author.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll(){
        return ResponseEntity.ok(authorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@ModelAttribute @Valid AuthorRequest request){
        return ResponseEntity.ok(authorService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @ModelAttribute @Valid AuthorRequest request) throws NotFoundException {
        return ResponseEntity.ok(authorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
