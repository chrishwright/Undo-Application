package com.wright.undoapp;

public class EmptyStackException  extends RuntimeException {
    
    private static final long serialVersionUID = 3979139566839115803L;

    public EmptyStackException(String message) {
        super(message);
    }

}
