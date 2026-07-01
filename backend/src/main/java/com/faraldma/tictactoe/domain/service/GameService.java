package com.faraldma.tictactoe.domain.service;

import com.faraldma.tictactoe.domain.model.Move;

import java.util.Optional;
import java.util.UUID;

import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;

public interface GameService {
    Optional<Move> getNextMove(Game game);
    Optional<Move> getNextMove(GameBoard gameBoard);
    boolean validateGame(GameBoard previousBoard, GameBoard currentBoard);
    Game makeMove(UUID gameId, GameBoard requestedBoard, UUID userId);
}
