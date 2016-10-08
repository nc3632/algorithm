import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        // Use size k min-heap. The top one would the kth largest.
        // Keep checking each number, if the number is less than top, then drop it.
        // Otherwise, pop the top out and then push the new number in.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // O(nlog(k))
        for(int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                pq.offer(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        KthLargestElement sol = new KthLargestElement();
        System.out.println(sol.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }
}
