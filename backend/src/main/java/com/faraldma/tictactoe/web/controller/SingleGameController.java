package com.faraldma.tictactoe.web.controller;

import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.domain.service.GameService;
import com.faraldma.tictactoe.security.AuthService;
import com.faraldma.tictactoe.web.mapper.GameWebMapper;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.GameBoard;
import com.faraldma.tictactoe.web.model.GameResponse;
import com.faraldma.tictactoe.web.model.GameUpdateRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;
import lombok.AllArgsConstructor;

@Tag(name = "Single game controller")
@RestController
@RequestMapping("/api/v1/game")
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class SingleGameController {
    private GameRepository repository;
    private GameService service;
    private AuthService authService;
    private GameWebMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(SingleGameController.class);

    @Operation(summary = "Make a move in a game with computer", description = "Update gameboard and returns the board with computer response")
    @PostMapping("/{gameId}")
    public ResponseEntity<?> updateGame(
        @PathVariable UUID gameId,
        @RequestBody GameUpdateRequest request
    ) {
        GameBoard requestedBoard = mapper.toDomainBoard(request);
        Game updateGame = service.makeMove(gameId, requestedBoard, authService.getUserId());
        repository.save(updateGame);
        return ResponseEntity.ok(mapper.toResponse(updateGame));
    }
    
    @PostMapping
    public ResponseEntity<GameResponse> createGame(@RequestParam(defaultValue = "3") int size) {
        UUID gameId = UUID.randomUUID();
        Game game = new Game(gameId, new GameBoard(size), authService.getUserId());
        log.info("Game created: {}", gameId);
        repository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(game));
    }


}
