/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 */

public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            // Count the total number of ones at each bit
            int numOfOnes = 0;
            for (int n : nums) {
                numOfOnes += n >> i & 1;
            }

            // Modulus 3 would get rid of those ones from
            // the numbers that appear three times
            result |= (numOfOnes % 3) << i;
        }

        return result;
    }
}
