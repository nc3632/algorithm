import java.util.Arrays;

/**
 * Implement prev permutation, which rearranges numbers into the lexicographically previous smaller permutation of
 * numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the highest possible order (ie, sorted in descending
 * order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * 1,3,2 -> 1,2,3
 * 1,2,3 -> 3,2,1
 * 1,5,1 -> 1,1,5
 */

public class PrevPermutation {
    public void prevPermutation(int[] nums) {
        int pos = nums.length - 1;
        while (nums[pos] < nums[pos - 1]) {
            pos--;
        }

        if (pos != nums.length - 1) {
            int temp = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= pos; i--) {
                nums[i + 1] = nums[i];
            }
            nums[pos] = temp;
        } else {
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        PrevPermutation sol = new PrevPermutation();

        int[] nums = new int[]{1, 3, 2};
        sol.prevPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 5, 1};
        sol.prevPermutation(nums);
        System.out.println(Arrays.toString(nums));

        nums = new int[]{1, 2, 3};
        sol.prevPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
