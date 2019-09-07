package com.test.dummy.Exception;

public class OrderInvalidException extends Exception{

    public OrderInvalidException(String errorMessage) {
        super(errorMessage);
    }
}
