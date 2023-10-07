package com.ken.bookstore.exceptions;

public class AlreadyExistsException extends Exception {

    public AlreadyExistsException(String message){
        super(message);
    }
}
