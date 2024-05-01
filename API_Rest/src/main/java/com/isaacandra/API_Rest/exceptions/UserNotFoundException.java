package com.isaacandra.API_Rest.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){super("Users Not Found");
    }
    public UserNotFoundException(String message){
        super(message);
    }

}
