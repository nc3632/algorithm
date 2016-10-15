import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

public class TopKFrequentElementsLinear {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        Random random = new Random();
        List<Integer> result = new ArrayList<>(k);
        int start = 0, end = list.size() - 1;
        while (true) {
            int pivot = start + random.nextInt(end - start + 1);
            pivot = divide(list, start, end, pivot);

            if (pivot == k - 1) {
                for (int i = 0; i < k; i++) {
                    result.add(list.get(i).getKey());
                }
                return result;
            } else if (pivot < k - 1) {
                start = pivot + 1;
            } else {
                end = pivot - 1;
            }
        }
    }

    private int divide(List<Map.Entry<Integer, Integer>> nums, int start, int end, int pivot) {
        swap(nums, pivot, end);

        int reference = nums.get(end).getValue();
        for (int i = start; i < end; i++) {
            if (nums.get(i).getValue() > reference) {
                swap(nums, start, i);
                start++;
            }
        }

        swap(nums, start, end);

        return start;
    }

    private void swap(List<Map.Entry<Integer, Integer>> nums, int x, int y) {
        Map.Entry<Integer, Integer> temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }

    public static void main(String[] args) {
        TopKFrequentElementsLinear sol = new TopKFrequentElementsLinear();
        System.out.println(sol.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2));
    }
}
