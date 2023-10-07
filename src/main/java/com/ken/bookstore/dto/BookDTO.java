package com.ken.bookstore.dto;

import com.ken.bookstore.models.Book;
import lombok.Data;

import java.util.Date;

@Data
public class BookDTO {

    private Long id;

    private String title;

    private String summary;

    private String image;

    private Double price;

    private Long author;

    private Date createdAt;

    public BookDTO(Book book){
        this.id = book.getId();
        this.title = book.getTitle();
        this.summary = book.getSummary();
        this.image = book.getImage();
        this.price = book.getPrice();
        this.author = book.getAuthor().getId();
        this.createdAt = book.getCreatedAt();
    }

}
