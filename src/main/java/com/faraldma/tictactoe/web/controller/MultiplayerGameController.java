package com.faraldma.tictactoe.web.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.domain.model.Move;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;
import com.faraldma.tictactoe.domain.service.TwoPlayerGameService;
import com.faraldma.tictactoe.security.AuthService;
import com.faraldma.tictactoe.web.mapper.GameWebMapper;
import com.faraldma.tictactoe.web.model.GameResponse;
import com.faraldma.tictactoe.web.model.MoveRequset;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/multiplayer")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MultiplayerGameController {
    private GameRepository gameRepository;
    private TwoPlayerGameService gameService;
    private AuthService authService;
    private GameWebMapper mapper;

    @PostMapping
    public ResponseEntity<GameResponse> createGame() {
        TwoPlayerGame game = gameService.createGame(authService.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(game));
    }

    @PostMapping("/{gameId}/join")
    public ResponseEntity<GameResponse> joinGame(
        @PathVariable UUID gameId
    ) {
        TwoPlayerGame game = gameService.joinGame(gameId, authService.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(game));
    }

    @PostMapping("/{gameId}/move")
    public ResponseEntity<?> makeMove(
        @PathVariable UUID gameId,
        @RequestBody MoveRequset request
    ) {
        UUID userId = authService.getUserId();
        Move move = mapper.toDomainMove(request);
        TwoPlayerGame game = gameService.makeMove(gameId, userId, move);
        gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(game));
    }
}
