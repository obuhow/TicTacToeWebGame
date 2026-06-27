package com.faraldma.tictactoe.web.controller;

import java.nio.file.AccessDeniedException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.faraldma.tictactoe.exception.GameNotFoundException;
import com.faraldma.tictactoe.exception.GameOverException;
import com.faraldma.tictactoe.exception.InvalidMoveException;
import com.faraldma.tictactoe.exception.InvalidStateException;
import com.faraldma.tictactoe.exception.MoveCalculatingException;
import com.faraldma.tictactoe.exception.WrongMappingException;
import com.faraldma.tictactoe.web.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleGameNotFound(GameNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(WrongMappingException.class)
    public ResponseEntity<ErrorResponse> handleWrongMapping(WrongMappingException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(MoveCalculatingException.class) 
    public ResponseEntity<ErrorResponse> handleMoveCalculatingException(MoveCalculatingException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InvalidMoveException.class)
    public ResponseEntity<ErrorResponse> handleInvalidMove(InvalidMoveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidState(InvalidStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(GameOverException.class)
    public ResponseEntity<ErrorResponse> handleGameOver(GameOverException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
    }
}
