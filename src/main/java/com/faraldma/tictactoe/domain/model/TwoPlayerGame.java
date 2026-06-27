package com.faraldma.tictactoe.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faraldma.tictactoe.enums.GameStatus;
import com.faraldma.tictactoe.enums.GameType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TwoPlayerGame extends Game {
    private UUID secondPlayerId;

    public TwoPlayerGame(UUID gameId, GameBoard board, UUID firstPlayerId, GameType type, GameStatus status) {
        super(gameId, board, firstPlayerId, type, status, null);
        secondPlayerId = null;
    }

    public TwoPlayerGame(UUID gameId, GameBoard board,  UUID firstPlayerId, UUID secondPlayerId,
             GameStatus status) {
        super(gameId, board, firstPlayerId, GameType.MULTIPLAYER, status, null);

    }

    public TwoPlayerGame(UUID gameId, GameBoard board, UUID firstPlayerId, GameType type,
            GameStatus status, LocalDateTime createdAt, UUID secondPlayerId) {
        super(gameId, board, firstPlayerId, type, status, createdAt);
        this.secondPlayerId = secondPlayerId;
    }
}
