import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */

public class MajoriyElementII {
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        int candidate1 = nums[0], candidate2 = nums[0];
        int votes1 = 0, votes2 = 0;
        for (int num : nums) {
            // Must compare with candidates first
            if (candidate1 == num) {
                votes1++;
            } else if (candidate2 == num) {
                votes2++;
            } else if (votes1 == 0) {
                candidate1 = num;
                votes1++;
            } else if (votes2 == 0) {
                candidate2 = num;
                votes2++;
            } else {
                votes1--;
                votes2--;
            }
        }

        // Need to recount to determine the majority
        // The reason is that the number of votes here do not
        // represent the actual number of appearances
        votes1 = 0;
        votes2 = 0;
        for (int num : nums) {
            if (candidate1 == num) {
                votes1++;
            } else if (candidate2 == num) {
                votes2++;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (votes1 > nums.length / 3) {
            result.add(candidate1);
        }

        if (votes2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        MajoriyElementII sol = new MajoriyElementII();
        List<Integer> result = sol.majorityElement(new int[]{4, 2, 1, 1});
        System.out.println(result.toString());
    }
}
