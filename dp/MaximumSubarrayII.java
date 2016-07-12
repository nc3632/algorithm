import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers, find two non-overlapping subarrays which have the largest sum.
 * The number in each subarray should be contiguous.
 * Return the largest sum.
 *
 * For given [1, 3, -1, 2, -1, 2], the two subarrays are [1, 3] and [2, -1, 2] or [1, 3, -1, 2] and [2],
 * they both have the largest sum 7.
 */

public class MaximumSubarrayII {
    public int maxTwoSubArrays(List<Integer> nums) {
        int[] maxFromLeft = new int[nums.size() + 1];
        for (int i = 1; i <= nums.size(); i++) {
            maxFromLeft[i] = Math.max(maxFromLeft[i - 1] + nums.get(i - 1), nums.get(i - 1));
        }

        for (int i = 2; i <= nums.size(); i++) {
            maxFromLeft[i] = Math.max(maxFromLeft[i], maxFromLeft[i - 1]);
        }

        int[] maxFromRight = new int[nums.size() + 1];
        for (int i = nums.size() - 1; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1] + nums.get(i), nums.get(i));
        }

        for (int i = nums.size() - 2; i >= 0; i--) {
            maxFromRight[i] = Math.max(maxFromRight[i + 1], maxFromRight[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < maxFromLeft.length; i++) {
            max = Math.max(maxFromLeft[i] + maxFromRight[i - 1], max);
        }

        return max;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList( -1,  -1);
        MaximumSubarrayII sol = new MaximumSubarrayII();
        System.out.println(sol.maxTwoSubArrays(list));
    }
}
