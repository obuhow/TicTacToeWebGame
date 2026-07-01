package com.faraldma.tictactoe.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Move {
    private final int x, y;
}
