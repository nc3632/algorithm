import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct numbers, return all possible permutations.
 *
 * For example,
 * [1,2,3] have the following permutations:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> candidate = new ArrayList<>();
        helper(nums, used, candidate, result);

        return result;
    }

    private void helper(int[] nums, boolean[] used, List<Integer> candidate, List<List<Integer>> result) {

        if (candidate.size() == nums.length) {
            result.add(new ArrayList<>(candidate));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                candidate.add(nums[i]);
                used[i] = true;

                helper(nums, used, candidate, result);

                candidate.remove(candidate.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations sol = new Permutations();
        System.out.println(sol.permute(new int[]{1, 2, 3}));
    }
}
