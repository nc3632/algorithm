import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are
 * adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 */

public class SortColor {
    public void sortColors(int[] nums) {
        int posZero = 0, posTwo = nums.length - 1;
        for (int i = 0; i <= posTwo;) {
            if (nums[i] == 1) {
                i++;
            } else if (nums[i] == 0) {
                swap(nums, posZero, i);
                posZero++;
                i++;
            } else {
                swap(nums, i, posTwo);
                posTwo--;
            }
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void main(String[] args) {
        SortColor sol = new SortColor();
        int[] nums = new int[]{2, 1, 0, 0, 1, 2, 1, 0, 2, 2, 0, 1};
        sol.sortColors(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{0, 0, 1, 1, 1, 2};
        sol.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
