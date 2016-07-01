/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 */

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n;
        }

        return result;
    }
}
