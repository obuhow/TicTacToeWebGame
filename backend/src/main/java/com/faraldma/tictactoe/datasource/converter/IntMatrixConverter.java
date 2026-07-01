package com.faraldma.tictactoe.datasource.converter;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class IntMatrixConverter implements AttributeConverter<int[][], String>{
    private static final ObjectMapper mapper = new ObjectMapper();

    public String convertToDatabaseColumn(int[][] board) {
        if (board == null) return null;
        try {
            return mapper.writeValueAsString(board);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert int[][] to JSON", e);
        }
    }

    public int[][] convertToEntityAttribute(String board) {
        if (board == null) return null;
        try {
            return mapper.readValue(board, int[][].class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to int[][]", e);
        }
    }
}
