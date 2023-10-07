package com.ken.bookstore.dto;

import com.ken.bookstore.models.User;
import lombok.Data;
import java.util.Date;

@Data
public class UserDTO {

    private Long id;

    private String name;

    private String country;

    private String email;

    private String role;

    private Date createdAt;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.country = user.getCountry();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.createdAt = user.getCreatedAt();
    }
}
