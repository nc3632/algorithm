/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
 * the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // Max sum with nums[i - 1] as the last element
        int prevMaxSum = 0;

        // Max sum with nums[i] as the last element
        int currMaxSum = 0;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            currMaxSum = Math.max(nums[i], prevMaxSum + nums[i]);
            max = Math.max(max, currMaxSum);

            prevMaxSum = currMaxSum;
        }

        return max;
    }
}
