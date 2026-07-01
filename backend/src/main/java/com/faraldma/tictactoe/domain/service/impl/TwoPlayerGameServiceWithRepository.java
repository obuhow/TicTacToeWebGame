package com.faraldma.tictactoe.domain.service.impl;

import static com.faraldma.tictactoe.domain.model.GameConstants.FREE_CELL;
import static com.faraldma.tictactoe.domain.model.GameConstants.PLAYER;
import static com.faraldma.tictactoe.domain.model.GameConstants.PLAYER_2;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;
import com.faraldma.tictactoe.domain.model.Minimax;
import com.faraldma.tictactoe.domain.model.Move;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;
import com.faraldma.tictactoe.domain.service.TwoPlayerGameService;
import com.faraldma.tictactoe.enums.GameStatus;
import com.faraldma.tictactoe.enums.GameType;
import com.faraldma.tictactoe.exception.GameNotFoundException;
import com.faraldma.tictactoe.exception.GameOverException;
import com.faraldma.tictactoe.exception.InvalidMoveException;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class TwoPlayerGameServiceWithRepository implements TwoPlayerGameService {
    private GameRepository gameRepository;

    private static final Logger log = LoggerFactory.getLogger(TwoPlayerGameServiceWithRepository.class);

    @Override
    public TwoPlayerGame createGame(UUID firstPlayerId) {
        TwoPlayerGame game = new TwoPlayerGame(UUID.randomUUID(), new GameBoard(), firstPlayerId, GameType.MULTIPLAYER, GameStatus.WAITING);
        gameRepository.save(game);
        return game;
    }

    @Override
    public TwoPlayerGame joinGame(UUID gameId, UUID secondPlayerId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        TwoPlayerGame twoPlayerGame = (TwoPlayerGame)game;
        twoPlayerGame.setSecondPlayerId(secondPlayerId);
        twoPlayerGame.setStatus(GameStatus.PLAYER_1_TURN);
        gameRepository.save(twoPlayerGame);
        return twoPlayerGame;
    }

    @Override
    public TwoPlayerGame makeMove(UUID gameId, UUID playerId, Move move) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        if (!(game instanceof TwoPlayerGame)) {
            throw new RuntimeException("Wrong type of game");
        }
        TwoPlayerGame twoPlayerGame = (TwoPlayerGame) game;
        
        if (twoPlayerGame.getBoard().getCell(move.getX(), move.getY()) != FREE_CELL) {
            throw new InvalidMoveException();
        }
        if (twoPlayerGame.isGameOver()) {
            throw new GameOverException(twoPlayerGame.getStatus());
        }

        if (playerId.equals(twoPlayerGame.getFirstPlayerId())) {
            twoPlayerGame.getBoard().setCell(move.getX(), move.getY(), PLAYER);
            if (Minimax.checkWin(twoPlayerGame, PLAYER)) {
                twoPlayerGame.setStatus(GameStatus.PLAYER_1_WON);
            } else if (Minimax.isFull(twoPlayerGame)) {
                twoPlayerGame.setStatus(GameStatus.DRAW);
            } else {
                twoPlayerGame.setStatus(GameStatus.PLAYER_2_TURN);
            }
        } else if (playerId.equals(twoPlayerGame.getSecondPlayerId())) {
            twoPlayerGame.getBoard().setCell(move.getX(), move.getY(), PLAYER_2);
            if (Minimax.checkWin(twoPlayerGame, PLAYER_2)) {
                twoPlayerGame.setStatus(GameStatus.PLAYER_2_WON);
            } if (Minimax.isFull(twoPlayerGame)) {
                twoPlayerGame.setStatus(GameStatus.DRAW);
            } else {
                twoPlayerGame.setStatus(GameStatus.PLAYER_1_TURN);
            }
        } else {
             throw new UsernameNotFoundException("User has not join the game");
        }

        return twoPlayerGame;
    }
}
