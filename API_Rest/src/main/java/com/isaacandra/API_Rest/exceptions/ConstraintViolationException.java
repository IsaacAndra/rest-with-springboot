package com.isaacandra.API_Rest.exceptions;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(){super("The Fields Cannot Be Null");}
    public ConstraintViolationException(String message){super(message);}

}
