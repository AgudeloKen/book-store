package com.ken.bookstore.controllers;

import com.ken.bookstore.dto.BookDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.BookRequest;
import com.ken.bookstore.services.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll(@PageableDefault Pageable pageable){
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@ModelAttribute @Valid BookRequest request) throws NotFoundException {
        return ResponseEntity.ok(bookService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @ModelAttribute @Valid BookRequest request) throws NotFoundException {
        return ResponseEntity.ok(bookService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
