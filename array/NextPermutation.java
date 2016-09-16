/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
 * order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
 * column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 */

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int violationIndex = nums.length - 2;
        while (violationIndex >= 0 && nums[violationIndex] >= nums[violationIndex + 1]) {
            violationIndex--;
        }

        if (violationIndex != -1) {
            int candidateIndex = nums.length - 1;
            while (nums[candidateIndex] <= nums[violationIndex]) {
                candidateIndex--;
            }
            swap(nums, candidateIndex, violationIndex);
        }

        reverse(nums, violationIndex + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
