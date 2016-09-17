import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of n elements which contains elements from 0 to n-1, with any of these numbers appearing any
 * number of times. Find these repeating numbers in O(n) and using only constant memory space.
 *
 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer should be 1, 3 and 6.
 */

public class FindDuplicates {
    public Set<Integer> findDuplicates(int[] arr, int n) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int origValue = Math.abs(arr[i]);

            if (arr[origValue] < 0) {
                result.add(origValue);
            } else {
                arr[origValue] = -arr[origValue];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindDuplicates sol = new FindDuplicates();
        System.out.println(sol.findDuplicates(new int[]{1, 2, 3, 1, 3, 0, 6, 1, 6, 0}, 7));
    }
}
