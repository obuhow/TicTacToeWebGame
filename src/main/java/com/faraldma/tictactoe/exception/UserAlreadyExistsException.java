package com.faraldma.tictactoe.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String login) {
        super("User already exists:" + login);
    }
}
