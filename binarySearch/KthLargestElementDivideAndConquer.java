import java.util.Random;

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

public class KthLargestElementDivideAndConquer {
    // O(n)
    public int findKthLargest(int[] nums, int k) {
        int start = 0, end = nums.length - 1;
        Random random = new Random();
        int pivot;
        while (true) {
            pivot = start + random.nextInt(end - start + 1);
            pivot = helper(nums, start, end, pivot);

            if (pivot == k - 1) {
                return nums[pivot];
            } else if (pivot > k - 1) {
                end = pivot - 1;
            } else {
                start = pivot + 1;
            }
        }
    }

    private int helper(int[] nums, int start, int end, int pivot) {
        swap(nums, pivot, end);

        for (int i = start; i < end; i++) {
            if (nums[i] > nums[end]) {
                swap(nums, start, i);
                start++;
            }
        }

        swap(nums, start, end);
        return start;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElementDivideAndConquer sol = new KthLargestElementDivideAndConquer();
        System.out.println(sol.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(sol.findKthLargest(new int[]{-1, -2}, 2));
        System.out.println(sol.findKthLargest(new int[]{-1, 2, 0}, 3));
        System.out.println(sol.findKthLargest(new int[]{-1, 2, 0}, 2));
        System.out.println(sol.findKthLargest(new int[]{-1, 2, 0}, 1));
    }
}
