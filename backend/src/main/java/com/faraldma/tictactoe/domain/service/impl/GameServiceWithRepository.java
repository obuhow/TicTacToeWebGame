package com.faraldma.tictactoe.domain.service.impl;

import com.faraldma.tictactoe.domain.service.GameService;
import com.faraldma.tictactoe.exception.GameNotFoundException;
import com.faraldma.tictactoe.exception.GameOverException;
import com.faraldma.tictactoe.exception.InvalidMoveException;
import com.faraldma.tictactoe.exception.MoveCalculatingException;
import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;
import com.faraldma.tictactoe.domain.model.Minimax;
import com.faraldma.tictactoe.domain.model.Move;
import static com.faraldma.tictactoe.domain.model.GameConstants.SERVER;
import static com.faraldma.tictactoe.domain.model.GameConstants.FREE_CELL;

import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class GameServiceWithRepository implements GameService {
    private GameRepository gameRepository;

    public void save(Game game) {
        gameRepository.save(game);
    }

    public Optional<Game> findById(UUID gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public Optional<Move> getNextMove(Game game) {
        return Optional.of(Minimax.getBestMove(game));
    }

    @Override
    public Optional<Move> getNextMove(GameBoard gameBoard) {
        return Optional.of(Minimax.getBestMove(new Game(gameBoard)));
    }

    @Override
    public boolean validateGame(GameBoard previousGame, GameBoard currentGame) {
        int size = previousGame.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int previousCell = previousGame.getCell(i, j);
                int currentCell = currentGame.getCell(i, j);
                if (previousCell == currentCell) {
                    continue;
                } else {
                    if (previousCell != FREE_CELL || currentCell == SERVER)
                        return false;
                }
            }
        }
        return true;
    }

    @Override
    public Game makeMove(UUID gameId, GameBoard requestedBoard, UUID userId) {
        Game currentGame = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        currentGame.defineGameStatus();
        if (currentGame.isGameOver())
            throw new GameOverException(currentGame.getStatus());
        if (!validateGame(currentGame.getBoard(), requestedBoard)) 
            throw new InvalidMoveException();

        Move move = getNextMove(requestedBoard).orElseThrow(() -> new MoveCalculatingException());
        requestedBoard.setCell(move.getX(), move.getY(), SERVER);
        Game game = new Game(gameId, requestedBoard, userId);
        game.defineGameStatus();
        return game;
    }


}
