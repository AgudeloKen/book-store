package com.ken.bookstore.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotNull(message = "Can't be null.")
    private String name;

    @NotNull(message = "Can't be null.")
    private String country;

    @NotNull(message = "Can't be null.")
    private String email;

    @NotNull(message = "Can't be null.")
    private String password;

    private String role;
}
