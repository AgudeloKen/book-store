package com.ken.bookstore.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BookRequest {

    @NotNull(message = "Can't be null.")
    private String title;

    @NotNull(message = "Can't be null.")
    private String summary;

    @NotNull(message = "Can't be null.")
    private Long authorId;

    @NotNull(message = "Can't be null.")
    private MultipartFile image;

    @NotNull(message = "Can't be null.")
    private Double price;
}
