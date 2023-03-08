package com.shopp.store.customException;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}
