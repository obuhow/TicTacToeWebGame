package com.faraldma.tictactoe.datasource.mapper;


import com.faraldma.tictactoe.datasource.model.GameEntity;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;
import com.faraldma.tictactoe.enums.GameType;

public class GameMapper {
    public static GameEntity toEntity(Game game) {
        return new GameEntity(game.getGameId(), game.getBoard().getBoardDeepCopy(), game.getFirstPlayerId(), null, game.getStatus(), game.getType());
    }

    public static GameEntity toEntity(TwoPlayerGame game) {
        return new GameEntity(game.getGameId(), game.getBoard().getBoardDeepCopy(), game.getFirstPlayerId(), game.getSecondPlayerId(), game.getStatus(), game.getType());
    }

    public static Game toDomain(GameEntity entity) {
        if (entity.getType() == GameType.SINGLE_PLAYER) 
            return new Game(entity.getGameId(), new GameBoard(entity.getBoard()), entity.getFirstPlayerId(), entity.getType(), entity.getStatus(), entity.getCreatedAt());
        return new TwoPlayerGame(entity.getGameId(), new GameBoard(entity.getBoard()), entity.getFirstPlayerId(), entity.getType(), entity.getStatus(),  entity.getCreatedAt(), entity.getSecondPlayerId());
    }
}
