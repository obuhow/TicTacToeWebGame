package com.faraldma.tictactoe.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.faraldma.tictactoe.datasource.repository.GameCrudRepository;
import com.faraldma.tictactoe.datasource.repository.GameRepository;
import com.faraldma.tictactoe.datasource.repository.impl.PostgresGameRepository;
import com.faraldma.tictactoe.domain.service.GameService;
import com.faraldma.tictactoe.domain.service.TwoPlayerGameService;
import com.faraldma.tictactoe.domain.service.impl.GameServiceWithRepository;
import com.faraldma.tictactoe.domain.service.impl.TwoPlayerGameServiceWithRepository;
import com.faraldma.tictactoe.web.mapper.GameWebMapper;

@Configuration
public class AppConfig {
    
    @Bean
    public GameRepository gameRepository(GameCrudRepository crudRepository) {
        return new PostgresGameRepository(crudRepository);
    }

    @Bean
    public GameService gameService(GameRepository gameRepository) {
        return new GameServiceWithRepository(gameRepository);
    }

    @Bean
    public TwoPlayerGameService twoPlayerGameService(GameRepository gameRepository) {
        return new TwoPlayerGameServiceWithRepository(gameRepository);
    }

    @Bean
    public GameWebMapper gameWebMapper() {
        return new GameWebMapper();
    }
}
