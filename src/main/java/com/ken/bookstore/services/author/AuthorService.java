package com.ken.bookstore.services.author;

import com.ken.bookstore.dto.AuthorDTO;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.models.Author;
import com.ken.bookstore.repositories.AuthorRepository;
import com.ken.bookstore.requests.AuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<AuthorDTO> findAll() {
        return authorRepository.findAll().stream().map(AuthorDTO::new).toList();
    }

    @Override
    public AuthorDTO create(AuthorRequest request) {
        Author author = new Author();
        author.setName(request.getName());
        author.setImage(Objects.requireNonNull(request.getImage().getOriginalFilename()).replace(" ", "-"));
        author.setBiography(request.getBiography());

        return new AuthorDTO(authorRepository.save(author));
    }

    @Override
    public AuthorDTO findById(Long id) throws NotFoundException {
        if(!authorRepository.existsById(id)){
            throw new NotFoundException("Author not found.");
        }
        return new AuthorDTO(authorRepository.getReferenceById(id));
    }

    @Override
    public AuthorDTO update(Long id, AuthorRequest request) throws NotFoundException {
        if(!authorRepository.existsById(id)){
            throw new NotFoundException("Author not found.");
        }
        Author author = authorRepository.getReferenceById(id);
        author.setName(request.getName());
        author.setImage(Objects.requireNonNull(request.getImage().getOriginalFilename()).replace(" ", "-"));
        author.setBiography(request.getBiography());
        return new AuthorDTO(authorRepository.save(author));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(!authorRepository.existsById(id)){
            throw new NotFoundException("Author not found.");
        }
        authorRepository.deleteById(id);
    }
}
