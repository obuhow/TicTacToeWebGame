package com.faraldma.tictactoe.web.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.faraldma.tictactoe.enums.GameStatus;
import com.faraldma.tictactoe.enums.GameType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class GameSummaryResponse {
    private UUID gameId;
    private GameType type;
    private GameStatus status;
    private LocalDateTime createdAt;
}
