package com.ken.bookstore.services.user;

import com.ken.bookstore.dto.UserDTO;
import com.ken.bookstore.exceptions.AlreadyExistsException;
import com.ken.bookstore.exceptions.NotFoundException;
import com.ken.bookstore.requests.UserRequest;
import java.util.List;

public interface IUserService {

    List<UserDTO> findAll();

    UserDTO save(UserRequest user) throws AlreadyExistsException;

    UserDTO findById(Long id) throws NotFoundException;

    UserDTO update(Long id, UserRequest user) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

}
