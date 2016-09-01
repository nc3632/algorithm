/**
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that he
 * will not get too much attention. This time, all houses at this place are arranged in a circle. That means the
 * first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as
 * for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount
 * of money you can rob tonight without alerting the police.
 */

public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else {
            return Math.max(helper(nums, 0, nums.length - 2), helper(nums, 1, nums.length - 1));
        }
    }

    private int helper(int[] nums, int start, int end) {
        int robIt = 0, notRobIt = 0;
        for (int i = start; i <= end; i++) {
            int newRobIt = notRobIt + nums[i];
            int newNotRobIt = Math.max(robIt, notRobIt);

            robIt = newRobIt;
            notRobIt = newNotRobIt;
        }

        return Math.max(robIt, notRobIt);
    }
}
