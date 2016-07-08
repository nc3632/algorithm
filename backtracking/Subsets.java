import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,3], a solution is:
 * [[3],[1],[2],[1,2,3],[1,3],[2,3],[1,2],[]]
 */

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        helper(nums, 0, result, new ArrayList<>());

        return result;
    }

    private void helper(int[] nums, int start, List<List<Integer>> result, List<Integer> candidate) {
        result.add(new ArrayList<>(candidate));

        for (int i = start; i < nums.length; i++) {
            candidate.add(nums[i]);
            helper(nums, i + 1, result, candidate);
            candidate.remove(candidate.size() - 1);
        }
    }
}
