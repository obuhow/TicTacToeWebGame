package com.faraldma.tictactoe.exception;

import com.faraldma.tictactoe.enums.GameStatus;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(GameStatus status) {
        super("Invalid state: " + status);
    }
}
