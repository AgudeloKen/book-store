package com.ken.bookstore.services.book;

import com.ken.bookstore.dto.BookDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.BookRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {

    List<BookDTO> findAll(Pageable pageable);

    BookDTO save(BookRequest bookRequest) throws NotFoundException;

    BookDTO findById(Long id) throws NotFoundException;

    BookDTO update(Long id, BookRequest bookRequest) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
