package com.faraldma.tictactoe.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CellValue {
    EMPTY(' '),
    PLAYER_X('X'),
    PLAYER_O('O');

    public final char mark;
}
