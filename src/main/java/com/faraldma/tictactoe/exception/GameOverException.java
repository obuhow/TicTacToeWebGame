package com.faraldma.tictactoe.exception;

import com.faraldma.tictactoe.enums.GameStatus;

public class GameOverException extends RuntimeException{
    public GameOverException(GameStatus status) {
        super("Game over: " + status.name());
    }
}
