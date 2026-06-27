package com.faraldma.tictactoe.domain.service;

import java.util.UUID;

import com.faraldma.tictactoe.domain.model.Move;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;

public interface TwoPlayerGameService {
    TwoPlayerGame createGame(UUID firstPlayerId);
    TwoPlayerGame joinGame(UUID gameId, UUID secondPlayerId);
    TwoPlayerGame makeMove (UUID gameId, UUID playerId, Move move);
}
