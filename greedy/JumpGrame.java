/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 *
 * A = [3,2,1,0,4], return false.
 */

public class JumpGrame {
    public boolean canJump(int[] nums) {
        int pos = 0;
        for (int max = 0; pos < nums.length && pos <= max; pos++) {
            max = Math.max(max, pos + nums[pos]);
        }

        return pos == nums.length;
    }
}
