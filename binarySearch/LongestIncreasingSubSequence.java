import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than
 * one LIS combination, it is only necessary for you to return the length.
 *
 * Your algorithm should run in O(n2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */

public class LongestIncreasingSubSequence {
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }

        List<Integer> seq = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            updateSequence(seq, nums[i]);
        }

        return seq.size();
    }

    private void updateSequence(List<Integer> seq, int elem) {
        int start = 0, end = seq.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (seq.get(mid) == elem) {
                return;
            } else if (seq.get(mid) < elem) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        if (start >= seq.size()) {
            seq.add(elem);
        } else {
            seq.set(start, elem);
        }
    }

    public static void main(String[] args) {
        LongestIncreasingSubSequence sol = new LongestIncreasingSubSequence();
        System.out.println(sol.lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println(sol.lengthOfLIS(new int[] {4, 2, 4, 4, 5, 3, 7}));
    }
}
