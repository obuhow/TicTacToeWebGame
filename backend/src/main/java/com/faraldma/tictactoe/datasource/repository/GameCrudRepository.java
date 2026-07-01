package com.faraldma.tictactoe.datasource.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import com.faraldma.tictactoe.datasource.model.GameEntity;

public interface GameCrudRepository extends CrudRepository<GameEntity, UUID> {
    List<GameEntity> findByFirstPlayerId(UUID userId);
    List<GameEntity> findBySecondPlayerId(UUID userId);
}