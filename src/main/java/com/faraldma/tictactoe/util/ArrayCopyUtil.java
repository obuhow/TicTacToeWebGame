package com.faraldma.tictactoe.util;

import java.util.Arrays;

public class ArrayCopyUtil {
    private ArrayCopyUtil() {}

    public static int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }
        int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = (original[i] == null) ? null : Arrays.copyOf(original[i], original[i].length);
        }
        return result;
    }
}
