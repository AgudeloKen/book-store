package com.ken.bookstore.dto;

import com.ken.bookstore.models.Author;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AuthorDTO {

    private Long id;

    private String name;

    private String biography;

    private String image;

    private List<Long> books = new ArrayList<>();

    private Date createdAt;

    public AuthorDTO(Author author){
        this.id = author.getId();
        this.name = author.getName();
        this.biography = author.getBiography();
        this.image = author.getImage();
        author.getBooks().forEach(b -> this.books.add(b.getId()));
        this.createdAt = author.getCreatedAt();
    }

}
