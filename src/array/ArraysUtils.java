package array;

import java.util.Arrays;

/**
 * @description: { }
 * @author: liudongyang
 * @date: 2019/12/23 15:10
 */
public class ArraysUtils {
    public static void printArray(int[][] ts) {
        for (int i = 0; i < ts.length; i++) {
            System.out.println(Arrays.toString(ts[i]));
        }
    }
}
