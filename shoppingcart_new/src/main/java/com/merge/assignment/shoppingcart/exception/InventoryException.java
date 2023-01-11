package com.merge.assignment.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Quantity greater than the stock")
public class InventoryException extends Exception {

    public InventoryException() {
        super();
    }

    public InventoryException(String message) {
        super(message);
    }

}