package com.faraldma.tictactoe.exception;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException() {
        super("Invalid move");
    }
}
