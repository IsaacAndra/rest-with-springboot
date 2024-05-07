package com.isaacandra.API_Rest.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(){super("Book Not Found");}
    public BookNotFoundException(String message){
        super(message);
    }
}
