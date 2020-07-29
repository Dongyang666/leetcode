package array;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author liudongyang
 * @date 2020/06/05
 */
public class SpiralOrder {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}})));
    }

    public static int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int row = matrix.length;
        int col = matrix[0].length;
        int[] res = new int[matrix[0].length * matrix.length];
        int circle = ((Math.min(row, col)) + 1) / 2;
        int index = 0;
        for (int i = 0; i < circle; ++i) {
            // 向左打印
            for (int j = i; j < col - i; j++) {
                res[index++] = matrix[i][j];
            }
            // 向下打印
            for (int j = i + 1; j < row - i; j++) {
                res[index++] = matrix[j][col - 1 - i];
            }

            // 向右打印
            for (int j = col - 1 - i - 1; j >= i && i * 2 != row - 1; --j) {
                res[index++] = matrix[row - 1 - i][j];
            }
            // 向上打印
            for (int j = row - 1 - i - 1; j > i && i * 2 != col - 1; --j) {
                res[index++] = matrix[j][i];
            }
        }
        return res;
    }
}
