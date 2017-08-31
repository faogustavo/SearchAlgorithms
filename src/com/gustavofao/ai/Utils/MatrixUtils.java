package com.gustavofao.ai.Utils;

public class MatrixUtils {

    public static int[][] cloneMatrix(int[][] matrix) {
        int[][] clonedMatrix = new int[matrix.length][];

        for (int i = 0; i < matrix.length; i++) {
            int jSize = matrix[i].length;
            clonedMatrix[i] = new int[jSize];

            for (int j = 0; j < jSize; j++) {
                clonedMatrix[i][j] = matrix[i][j];
            }
        }

        return clonedMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%d ", matrix[i][j]);
            }

            System.out.println();
        }
    }

}
