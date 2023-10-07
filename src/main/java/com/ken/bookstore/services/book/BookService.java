package com.ken.bookstore.services.book;


import com.ken.bookstore.dto.BookDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.models.Book;
import com.ken.bookstore.repositories.AuthorRepository;
import com.ken.bookstore.repositories.BookRepository;
import com.ken.bookstore.requests.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    @Override
    public List<BookDTO> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream().map(BookDTO::new).toList();
    }

    @Override
    public BookDTO save(BookRequest bookRequest) throws NotFoundException {
        if(!authorRepository.existsById(bookRequest.getAuthorId())){
            throw new NotFoundException("Author not found.");
        }
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setSummary(bookRequest.getSummary());
        book.setAuthor(authorRepository.getReferenceById(bookRequest.getAuthorId()));
        book.setImage(Objects.requireNonNull(bookRequest.getImage().getOriginalFilename()).replace(" ", "-"));
        book.setPrice(bookRequest.getPrice());
        return new BookDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO findById(Long id) throws NotFoundException {
        if(!bookRepository.existsById(id)){
            throw new NotFoundException("book not found.");
        }
        return new BookDTO(bookRepository.getReferenceById(id));
    }

    @Override
    public BookDTO update(Long id, BookRequest bookRequest) throws NotFoundException {
        if(!bookRepository.existsById(id)){
            throw new NotFoundException("book not found.");
        }

        Book book = bookRepository.getReferenceById(id);
        book.setTitle(bookRequest.getTitle());
        book.setSummary(bookRequest.getSummary());
        book.setImage(Objects.requireNonNull(bookRequest.getImage().getOriginalFilename()).replace(" ", "-"));
        book.setAuthor(authorRepository.getReferenceById(bookRequest.getAuthorId()));
        book.setPrice(bookRequest.getPrice());

        return new BookDTO(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!bookRepository.existsById(id)){
            throw new NotFoundException("book not found.");
        }
        bookRepository.deleteById(id);
    }
}
