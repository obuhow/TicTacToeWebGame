package com.faraldma.tictactoe.datasource.repository;

import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {
    void save(Game game);
    void save(TwoPlayerGame game);
    Optional<Game> findById(UUID gameId);
    List<Game> getAllGames();
    List<Game> getMyGames(UUID UserId);
}
