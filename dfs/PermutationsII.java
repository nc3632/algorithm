import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */

public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        boolean[] used = new boolean[nums.length];
        List<Integer> candidate = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        helper(nums, used, candidate, result);

        return result;
    }

    private void helper(int[] nums, boolean[] used, List<Integer> candidate, List<List<Integer>> result) {
        if (nums.length == candidate.size()) {
            result.add(new ArrayList<>(candidate));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i] || i != 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            candidate.add(nums[i]);

            helper(nums, used, candidate, result);

            used[i] = false;
            candidate.remove(candidate.size() - 1);
        }
    }

    public static void main(String[] args) {
        PermutationsII sol = new PermutationsII();
        System.out.println(sol.permuteUnique(new int[]{2, 1, 2, 1}));
    }
}
