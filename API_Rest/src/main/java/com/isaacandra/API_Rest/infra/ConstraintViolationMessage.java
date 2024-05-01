package com.isaacandra.API_Rest.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ConstraintViolationMessage {
    private HttpStatus status;
    private String message;
}
