package com.faraldma.tictactoe.exception;

public class WrongMappingException extends RuntimeException {
    public WrongMappingException() {
        super("Mapping Error");
    }
}
