package com.isaacandra.API_Rest.infra;

import com.isaacandra.API_Rest.exceptions.ConstraintViolationException;
import com.isaacandra.API_Rest.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<UserNotFoundMessage> userNotFoundHanler(UserNotFoundException e){
        UserNotFoundMessage threatResponse = new UserNotFoundMessage(HttpStatus.NOT_FOUND,e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ConstraintViolationMessage> violatedReqHandler(ConstraintViolationException e){
        ConstraintViolationMessage thraetResponse = new ConstraintViolationMessage(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(thraetResponse);
    }

}
