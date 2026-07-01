package com.faraldma.tictactoe.web.model;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class GameResponse {
    private UUID gameId;
    private int[][] board;
}
