/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                candidate = num;
                votes++;
            } else {
                if (candidate == num) {
                    votes++;
                } else {
                    votes--;
                }
            }
        }

        return candidate;
    }
}
