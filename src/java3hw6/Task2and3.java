package java3hw6;

import java.util.Arrays;

/**
 * @version 10.10.2018
 * @author Viktor Chernyaev
 * @Java3 homework Lesson-6
 * @link https://github.com/des1z
 */

public class Task2and3 {
    public static void main(String[] args) throws RuntimeException {
        Task2and3 hw = new Task2and3();
        System.out.println(Arrays.toString(hw.getNumberAfrerLastFour(
                new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7})));
        System.out.println(Arrays.toString(hw.getNumberAfrerLastFour(
                new int[]{1, 2, 4, 4})));
        System.out.println(
                hw.validateArrayByOneAndFour(new int[]{1, 4, 4, 4, 1, 4}));
        System.out.println(
                hw.validateArrayByOneAndFour(new int[]{1, 4, 5}));
    }

    int[] getNumberAfrerLastFour(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                int idx = i + 1;
                int[] result = new int[array.length - idx];
                System.arraycopy(array, idx, result, 0, result.length);
                return result;
            }
        throw new RuntimeException("There are no numbers 4");
    }

    boolean validateArrayByOneAndFour(int[] array) {
        boolean containsOne = false;
        boolean containsFour = false;
        for (int i : array)
            if (i == 1)
                containsOne = true;
            else if (i == 4)
                containsFour = true;
            else
                return false;
        return containsOne && containsFour;
    }
}