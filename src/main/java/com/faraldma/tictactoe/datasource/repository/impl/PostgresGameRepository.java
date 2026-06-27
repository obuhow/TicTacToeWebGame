package com.faraldma.tictactoe.datasource.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.faraldma.tictactoe.datasource.mapper.GameMapper;
import com.faraldma.tictactoe.datasource.model.GameEntity;
import com.faraldma.tictactoe.datasource.repository.GameCrudRepository;
import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.domain.model.Game;
import com.faraldma.tictactoe.domain.model.TwoPlayerGame;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PostgresGameRepository implements GameRepository {
    public final GameCrudRepository crudRepository;

    private static final Logger log = LoggerFactory.getLogger(PostgresGameRepository.class);

    @Override
    public void save(Game game) {
        GameEntity entity = GameMapper.toEntity(game);
        log.info("{} {}", game.getGameId(), entity.getGameId());
        crudRepository.save(entity);
    }

    @Override
    public void save(TwoPlayerGame game) {
        GameEntity entity = GameMapper.toEntity(game);
        log.info("{} {}", game.getGameId(), entity.getGameId());
        crudRepository.save(entity);
    }

    @Override
    public Optional<Game> findById(UUID gameId) {
        return crudRepository.findById(gameId).map(GameMapper::toDomain);
    }

    @Override
    public List<Game> getAllGames() {
        return StreamSupport.stream(crudRepository.findAll().spliterator(), false)
            .map(GameMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Game> getMyGames(UUID userId) {
        List<GameEntity> listOfGamesWherePlayerIsFirst = crudRepository.findByFirstPlayerId(userId);
        List<GameEntity> listOfGamesWherePlayerIsSecond = crudRepository.findBySecondPlayerId(userId);
        List<GameEntity> all = new ArrayList<>(listOfGamesWherePlayerIsFirst);
        all.addAll(listOfGamesWherePlayerIsSecond);
        return all.stream().map(GameMapper::toDomain).collect(Collectors.toList());
    }
}
