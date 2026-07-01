package com.faraldma.tictactoe.datasource.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.faraldma.tictactoe.datasource.converter.IntMatrixConverter;
import com.faraldma.tictactoe.enums.GameStatus;
import com.faraldma.tictactoe.enums.GameType;
import com.faraldma.tictactoe.util.ArrayCopyUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode
@Table(name = "games")
@Getter
@Setter
public class GameEntity {
    @Id
    private UUID gameId;

    @Convert(converter = IntMatrixConverter.class)
    @Column(columnDefinition = "TEXT")
    private int[][] board;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "first_player_id")
    private UUID firstPlayerId;

    @Column(name = "second_player_id")
    private UUID secondPlayerId;

    @Enumerated(EnumType.STRING)
    @Column
    private GameStatus status;

    @Enumerated(EnumType.STRING)
    @Column
    private GameType type;

    public GameEntity() {
        this.gameId = null;
        this.board = null;
        this.createdAt = null;
        this.firstPlayerId = null;
        this.secondPlayerId = null;
        this.status = GameStatus.WAITING;
    }

    public GameEntity(UUID gameId, int[][] board) {
        this.gameId = gameId;
        this.board = ArrayCopyUtil.deepCopy(board);
        this.createdAt = null;
        this.firstPlayerId = null;
        this.secondPlayerId = null;
        this.status = GameStatus.WAITING;
    }

    public GameEntity(UUID gameId, int[][] board, UUID firstPlayerId, GameType type) {
        this.gameId = gameId;
        this.board = ArrayCopyUtil.deepCopy(board);
        this.createdAt = null;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = null;
        this.status = GameStatus.WAITING;
        this.type = type;
    }

    public GameEntity(UUID gameId, int[][] board, UUID firstUserId, UUID secondUserId, GameStatus status, GameType type) {
        this.gameId = gameId;
        this.board = ArrayCopyUtil.deepCopy(board);
        this.createdAt = null;
        this.firstPlayerId = firstUserId;
        this.secondPlayerId = secondUserId;
        this.status = status;
        this.type = type;
    }

    public int[][] getBoard() {
        return ArrayCopyUtil.deepCopy(board);
    }
}
