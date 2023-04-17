package com.shopp.store.customException;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super();
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
    public ResourceNotFoundException(String errorMessage, Throwable cause){
        super(errorMessage, cause);
    }
    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
    protected ResourceNotFoundException(String message, Throwable cause,
                                        boolean enableSuppression,
                                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
