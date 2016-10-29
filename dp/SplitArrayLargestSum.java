/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * Given m satisfies the following constraint: 1 ≤ m ≤ length(nums) ≤ 14,000.
 *
 * Examples:
 *
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 *
 * Output:
 * 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */

public class SplitArrayLargestSum {
    /**
     * dp[s, i] represents the best value that can be obtained with s splits starting from nums[i] to the end.
     * so dp[m, 0] is the value that we are looking for.
     *
     * dp[s, i] = min{max(dp[s - 1, j], nums[i] + ... num[j - 1]}, where i < j <= nums.length - s
     *
     * O(mn^2)
     */
    public int splitArrayDP(int[] nums, int m) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] += sum[i - 1] + nums[i - 1];
        }

        long[] dp = new long[nums.length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = sum[sum.length - 1] - sum[i];
        }

        for (int s = 1; s < m; s++) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j <= nums.length - s; j++) {
                    long leftPart = sum[j] - sum[i];
                    if (leftPart >= dp[i]) {
                        break;
                    }

                    dp[i] = Math.min(dp[i], Math.max(leftPart, dp[j]));
                }
            }
        }

        return (int) dp[0];
    }

    public int splitArrayBinarySearch(int[] nums, int m) {
        long sum = 0;
        int max = 0;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }

        // The best value should locate between max and max.
        return binarySearch(nums, max, sum, m);
    }

    private int binarySearch(int[] nums, long low, long high, int m) {
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (isValid(nums, mid, m)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return (int) high;
    }

    private boolean isValid(int[] nums, long candidate, int m) {
        int count = 1;
        long total = 0;
        for (int n : nums) {
            total += n;
            if (total > candidate) {
                count++;
                if (count > m) {
                    return false;
                }
                total = n;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum sol = new SplitArrayLargestSum();
        System.out.println(sol.splitArrayBinarySearch(new int[]{7, 2, 5, 10, 8}, 2));
    }
}
