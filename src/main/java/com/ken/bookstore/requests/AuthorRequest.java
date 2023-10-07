package com.ken.bookstore.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AuthorRequest {

    @NotNull(message = "Can't be null.")
    private String name;

    @NotNull(message = "Can't be null.")
    private MultipartFile image;

    @NotNull(message = "Can't be null.")
    private String biography;

}
