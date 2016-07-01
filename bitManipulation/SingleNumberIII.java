/**
 * Given an array of numbers nums, in which exactly two elements appear only once
 * and all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 */

public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        // Find the XOR result of the two single numbers
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        // Find the last one bit of xor
        int lastOneBit = xor - (xor & (xor - 1));

        // Obviously the last one bit of the two single numbers
        // must be different. The following code would divide
        // nums array into different groups, and the two single
        // numbers will be located in different groups.
        int[] result = new int[2];
        for (int n : nums) {
            if ((n & lastOneBit) == 0) {
                result[0] ^= n;
            } else {
                result[1] ^= n;
            }
        }

        return result;
    }
}
