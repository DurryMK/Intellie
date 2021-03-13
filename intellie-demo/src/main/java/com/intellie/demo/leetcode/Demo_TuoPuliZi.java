package com.intellie.demo.leetcode;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author durry
 * @version 1.0
 * @date 2021/2/22 9:16
 */
public class Demo_TuoPuliZi {

    public static void main(String[] args) {

        int[][] matrix = createMatrix(10, 10);

        isToeplitzMatrix(matrix);
    }

    public static int[][] createMatrix(int m, int n) {
        Random random = new Random();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j != n - 1)
                    System.out.print(matrix[i][j] + ",");
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }

        return matrix;
    }

    public static boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, r = 0, l = 0, length = 0;
        Set contain = new HashSet();

        for (int i = 0; i < m; i++) {
            r = 0;
            l = i;
            contain = new HashSet();
            contain.add(matrix[r][l]);
            for (int j = 0; j < m; j++) {
                r += 1;
                l += 1;
                if (r < m && l < n)
                    contain.add(matrix[r][l]);
                else
                    break;
            }
            if (contain.size() > 1)
                System.out.println("No");
        }

        return false;
    }
}
