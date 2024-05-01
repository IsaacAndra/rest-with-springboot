package com.isaacandra.API_Rest.exceptions;

public class ConstraintViolationException extends RuntimeException{

    public ConstraintViolationException(){super("There's a violation in requisition. Need to post all objects");}
    public ConstraintViolationException(String message){super(message);}

}
