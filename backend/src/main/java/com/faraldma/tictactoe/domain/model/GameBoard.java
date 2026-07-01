package com.faraldma.tictactoe.domain.model;

import com.faraldma.tictactoe.util.ArrayCopyUtil;
import lombok.Getter;

public class GameBoard {
    private final int[][] board;
    @Getter
    private final int size;

    public GameBoard() {
        this.size = 3;
        this.board = new int[size][size];
    }

    public GameBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
    }

    public GameBoard(int[][] board) {
        this.size = board.length;
        this.board = ArrayCopyUtil.deepCopy(board);
    }

    public int getCell(int x, int y) {
        return board[x][y];
    }

    public int setCell(int x, int y, int value) {
        return board[x][y] = value;
    }

    public GameBoard(GameBoard board) {
        int[][] boardCopy = board.getBoardDeepCopy();
        this.size = boardCopy.length;
        this.board = boardCopy;
    }

    public int[][] getBoardDeepCopy() {
        return ArrayCopyUtil.deepCopy(board);
    }
}
