package com.isaacandra.API_Rest.exceptions.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserNotFoundMessage {
    private int status;
    private String message;

}
