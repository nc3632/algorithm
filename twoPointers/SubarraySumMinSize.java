/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which
 * the sum ≥ s. If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */

public class SubarraySumMinSize {
    public int minSubArrayLen(int s, int[] nums) {
        int windowStart = 0, windowEnd = 0;
        int sum = 0, minLen = Integer.MAX_VALUE;
        while (windowEnd < nums.length) {
            sum += nums[windowEnd++];

            while (sum >= s) {
                minLen = Math.min(minLen, windowEnd - windowStart);
                sum -= nums[windowStart++];
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        SubarraySumMinSize sol = new SubarraySumMinSize();
        System.out.println(sol.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }
}
