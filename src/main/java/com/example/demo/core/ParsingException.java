package com.example.demo.core;

public class ParsingException extends Throwable {
    private static final long serialVersionUID = 1L;

    public ParsingException(String message, Throwable e) {
        super(message, e);
    }
}
