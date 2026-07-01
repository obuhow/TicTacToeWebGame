package com.faraldma.tictactoe.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faraldma.tictactoe.datasource.model.User;
import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.datasource.repository.UserRepository;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.exception.GameNotFoundException;
import com.faraldma.tictactoe.security.AuthService;
import com.faraldma.tictactoe.web.mapper.GameWebMapper;
import com.faraldma.tictactoe.web.model.GameResponse;
import com.faraldma.tictactoe.web.model.GameSummaryResponse;
import com.faraldma.tictactoe.web.model.UserInfoResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "Game Info controller")
@RestController
@RequestMapping("api/v1/info")
@AllArgsConstructor
public class GameInfoController {
    private GameRepository gameRepository;
    private UserRepository userRepository;
    private GameWebMapper mapper;
    private AuthService authService;

    @GetMapping("/{gameId}")
    public ResponseEntity<GameResponse> getGame(@PathVariable UUID gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(game));
    }

    @GetMapping("/")
    public ResponseEntity<List<GameSummaryResponse>> getGames() {
        List<Game> gamesList = gameRepository.getAllGames();
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(gamesList));
    }

    @GetMapping("/my_games")
    public ResponseEntity<List<GameSummaryResponse>> getMyGames() {
        List<Game> gamesList = gameRepository.getMyGames(authService.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(gamesList));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("UserId is not found"));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(user));
    }
}
