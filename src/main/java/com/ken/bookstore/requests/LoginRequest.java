package com.ken.bookstore.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotNull(message = "Can't be null.")
    private String email;

    @NotNull(message = "Can't be null.")
    private String password;
}
