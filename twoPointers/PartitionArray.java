/**
 * Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
 *
 * All elements < k are moved to the left
 * All elements >= k are moved to the right
 * Return the partitioning index, i.e the first index i nums[i] >= k.
 *
 * Notice
 *
 * You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
 *
 * If all elements in nums are smaller than k, then return nums.length
 *
 * Example
 * If nums = [3,2,2,1] and k=2, a valid answer is 1.
 */

public class PartitionArray {
    public int partitionArray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0, end = nums.length - 1;
        while (start <= end) {
            if (nums[start] < k) {
                start++;
                continue;
            }

            if (nums[end] >= k) {
                end--;
                continue;
            }

            if (start < end) {
                swap(nums, start, end);
                start++;
                end--;
            }
        }

        return start;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
