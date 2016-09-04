import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all
 * unique quadruplets in the array which gives the sum of target.
 *
 * Note: The solution set must not contain duplicate quadruplets.
 *
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *  [-1,  0, 0, 1],
 *  [-2, -1, 1, 2],
 *  [-2,  0, 0, 2]
 * ]
 */

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        return kSum(nums, 0, nums.length - 1, target, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int start, int end, int target, int k) {
        List<List<Integer>> result = new ArrayList<>();

        if (k == 2) {
            result = new ArrayList<>();
            for (int i = start, j = end; i < j;) {
                if (nums[i] + nums[j] == target) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j])));

                    do {
                        i++;
                    } while (i < j && nums[i] == nums[i - 1]);

                    do {
                        j--;
                    } while (i < j && nums[j] == nums[j + 1]);
                } else if (nums[i] + nums[j] < target) {
                    i++;
                } else {
                    j--;
                }
            }
        } else {
            for (int i = start; i <= end - k + 1; i++) {
                if (i != start && nums[i] == nums[i - 1]) {
                    continue;
                }

                List<List<Integer>> temp = kSum(nums, i + 1, end, target - nums[i], k - 1);
                if (!temp.isEmpty()) {
                    for (List<Integer> list : temp) {
                        list.add(0, nums[i]);
                    }
                    result.addAll(temp);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FourSum sol = new FourSum();
        System.out.println(sol.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0));
    }
}
