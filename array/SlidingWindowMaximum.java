import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
 * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 *
 * Note:
 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }

        Deque<Integer> deque = new ArrayDeque<Integer>();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);

            if (deque.getFirst() < i - k + 1) {
                deque.removeFirst();
            }

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.getFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum sol = new SlidingWindowMaximum();
        System.out.println(Arrays.toString(sol.maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(sol.maxSlidingWindow(new int[] {}, 0)));
        System.out.println(Arrays.toString(sol.maxSlidingWindow(new int[] {1, -1}, 1)));
    }
}
