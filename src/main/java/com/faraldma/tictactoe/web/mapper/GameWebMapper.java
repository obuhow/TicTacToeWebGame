package com.faraldma.tictactoe.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.faraldma.tictactoe.datasource.model.User;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;
import com.faraldma.tictactoe.domain.model.Move;
import com.faraldma.tictactoe.exception.WrongMappingException;
import com.faraldma.tictactoe.web.model.GameResponse;
import com.faraldma.tictactoe.web.model.GameSummaryResponse;
import com.faraldma.tictactoe.web.model.GameUpdateRequest;
import com.faraldma.tictactoe.web.model.MoveRequset;
import com.faraldma.tictactoe.web.model.UserInfoResponse;

public class GameWebMapper {
    public GameResponse toResponse(Game game) {
        if (game == null || game.getBoard() == null) throw new WrongMappingException();
        return new GameResponse(game.getGameId(), game.getBoard().getBoardDeepCopy());
    }

    public UserInfoResponse toResponse(User user) {
        if (user == null) throw new WrongMappingException();
        return new UserInfoResponse(user.getId(), user.getLogin());
    }

    public List<GameSummaryResponse> toResponse(List<Game> gamesList) {
        return gamesList.stream()
            .map(game -> new GameSummaryResponse(game.getGameId(), game.getType(), game.getStatus(), game.getCreatedAt()))
            .collect(Collectors.toList());
    }

    public GameBoard toDomainBoard(GameUpdateRequest request) {
        if (request == null || request.getBoard() == null) throw new WrongMappingException();
        return new GameBoard(request.getBoard());
    }

    public Move toDomainMove(MoveRequset request) {
        if (request == null) return null;
        return new Move(request.getX(), request.getY());
    }
}
