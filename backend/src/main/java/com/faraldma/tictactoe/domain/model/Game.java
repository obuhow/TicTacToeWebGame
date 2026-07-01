package com.faraldma.tictactoe.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faraldma.tictactoe.enums.GameStatus;
import com.faraldma.tictactoe.enums.GameType;
import static com.faraldma.tictactoe.domain.model.GameConstants.PLAYER;
import static com.faraldma.tictactoe.domain.model.GameConstants.SERVER;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Game {
    private UUID gameId;
    private final GameBoard board;
    private UUID firstPlayerId;
    private GameType type;
    private GameStatus status;
    private LocalDateTime createdAt;

    public Game (UUID gameId, GameBoard board, UUID firstPlayerId) {
        this.gameId = gameId;
        this.board = board;
        this.firstPlayerId = firstPlayerId;
        type = GameType.SINGLE_PLAYER;
    }

    public Game (Game game) {
        this.gameId = game.getGameId();
        this.board = new GameBoard(game.getBoard());
    }

    public void defineGameStatus() {
        if (Minimax.isFull(this)) setStatus(GameStatus.DRAW);
        if (Minimax.checkWin(this, PLAYER)) setStatus(GameStatus.PLAYER_1_WON);
        if (Minimax.checkWin(this, SERVER)) setStatus(GameStatus.PLAYER_2_WON);
    }

    public boolean isGameOver() {
        return Minimax.isFull(this) || Minimax.checkWin(this, PLAYER) || Minimax.checkWin(this, SERVER);
    }
}
