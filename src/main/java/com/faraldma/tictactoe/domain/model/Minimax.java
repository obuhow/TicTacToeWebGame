package com.faraldma.tictactoe.domain.model;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.faraldma.tictactoe.domain.service.impl.GameServiceWithRepository;
import static com.faraldma.tictactoe.domain.model.GameConstants.PLAYER;
import static com.faraldma.tictactoe.domain.model.GameConstants.SERVER;

public class Minimax {

    private static final Logger log = LoggerFactory.getLogger(GameServiceWithRepository.class);

    public static Move getBestMove(Game game) {
        Move bestMove = null;
        int bestScore = -1000;
        ArrayList<Move> availableMoves = getAvailableMoves(game);
        if (availableMoves.isEmpty()) return null;
        for (Move move : availableMoves) {
            GameBoard boardCopy = new GameBoard(game.getBoard());
            boardCopy.setCell(move.getX(), move.getY(), SERVER);
            Game newGame = new Game(boardCopy);
            int score = minimaxScore(newGame, 1, false);
            log.debug("Ход {} имеет оценку {}", move, score);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        log.debug("Выбран ход {} с оценкой {}", bestMove, bestScore);
        return bestMove;
    }

    private static int minimaxScore(Game game, int depth, boolean isServerTurn) {
        if (checkWin(game, SERVER)) return 1000 - depth;
        if (checkWin(game, PLAYER)) return -1000 + depth;
        if (isFull(game)) return 0;

        ArrayList<Move> availableMoves = getAvailableMoves(game);
        int bestScore = isServerTurn ? -1000 : 1000;
        for (Move move : availableMoves) {
            GameBoard boardCopy = new GameBoard(game.getBoard().getBoardDeepCopy());
            boardCopy.setCell(move.getX(), move.getY(), isServerTurn ? SERVER : PLAYER);
            Game newGame = new Game(game.getGameId(), boardCopy, game.getFirstPlayerId());
            int score = minimaxScore(newGame, depth + 1, !isServerTurn);
            bestScore = isServerTurn ? Math.max(bestScore, score) : Math.min(bestScore, score);
        } 
        return bestScore;
    }

    public static ArrayList<Move> getAvailableMoves(Game game) {
        int size = game.getBoard().getSize();
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game.getBoard().getCell(i, j) == 0) {
                    moves.add(new Move(i, j));
                }
            }
        }
        return moves;
    }

    public static boolean checkWin(Game game, int player) {
        int size = game.getBoard().getSize();
        GameBoard gameBoard = game.getBoard();
        for (int i = 0; i < size; i++) {
            boolean winRow = true;
            boolean winCol = true;
            for (int j = 0; j < size; j++) {
                if (gameBoard.getCell(i, j) != player)
                    winRow = false;
                if (gameBoard.getCell(j, i) != player)
                    winCol = false;
            }
            if (winRow || winCol) {
                return true;
            }
        }
        boolean winDiag1 = true;
        for (int i = 0; i < size; i++) {
            if (gameBoard.getCell(i, i) != player)
                winDiag1 = false;
        }
        if (winDiag1) return true;
        
        boolean winDiag2 = true;
        for (int i = 0; i < size; i++) {
            if (gameBoard.getCell(i, size - i - 1) != player)
                winDiag2 = false;
        }
        return winDiag2;
    }

    public static boolean isFull(Game game) {
        GameBoard board = game.getBoard();
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) == 0) 
                    return false;
            }
        }
        return true;
    }
}
