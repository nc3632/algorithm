import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.
 *
 * If there are multiple solutions, return any subset is fine.
 * Example 1:
 * nums: [1,2,3]
 * Result: [1,2] (of course, [1,3] will also be ok)
 *
 * Example 2:
 * nums: [1,2,4,8]
 * Result: [1,2,4,8]
 */

public class LargestDivisibleSubset {
    private static class Pair {
        int len;
        int prevIndex;

        public Pair(int len, int prevIndex) {
            this.len = len;
            this.prevIndex = prevIndex;
        }
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        Pair[] dp = new Pair[nums.length];
        Arrays.fill(dp, new Pair(0, 0));

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j >= 0 && dp[i].len < j + 2; j--) {
                if (nums[i] % nums[j] == 0 && dp[j].len + 1 > dp[i].len) {
                    dp[i] = new Pair(dp[j].len + 1, j);
                }
            }
        }

        int max = 0, maxIndex = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i].len > max) {
                max = dp[i].len;
                maxIndex = i;
            }
        }

        List<Integer> result = new LinkedList<>();
        int index = maxIndex;
        for (int i = 0; i < max; i++) {
            result.add(0, nums[index]);
            index = dp[index].prevIndex;
        }

        return result;
    }
}
