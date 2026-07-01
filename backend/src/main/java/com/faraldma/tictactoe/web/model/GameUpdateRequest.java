package com.faraldma.tictactoe.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.AllArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameUpdateRequest {
    private int[][] board;
}
