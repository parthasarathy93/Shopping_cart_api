package com.merge.assignment.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "User suspended")
public class UserSuspendedException extends Exception {

    public UserSuspendedException() {
        super();
    }

    public UserSuspendedException(String message) {
        super(message);
    }

}