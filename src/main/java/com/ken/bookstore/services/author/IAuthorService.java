package com.ken.bookstore.services.author;

import com.ken.bookstore.dto.AuthorDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.AuthorRequest;

import java.util.List;

public interface IAuthorService {

    List<AuthorDTO> findAll();

    AuthorDTO create(AuthorRequest request);

    AuthorDTO findById(Long id) throws NotFoundException;

    AuthorDTO update(Long id, AuthorRequest request) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
