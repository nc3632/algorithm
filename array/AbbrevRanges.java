import java.util.ArrayList;
import java.util.List;

/**
 * For numbers from 1 - 99, given a sequence of numbers, for example, [2, 3, 5, 6, 7, 14],
 * print out the numbers left in the form of "x-y".
 *
 * For the above example, the output should be 1,4,8-13,15-99.
 */

public class AbbrevRanges {
    public String abbrev(int[] arr) {
        List<String> result = new ArrayList<>();

        int start = 1;
        for (int i = 0; i < arr.length; i++) {
            helper(start, arr[i] - 1, result);
            start = arr[i] + 1;
        }

        helper(start, 99, result);

        return String.join(",", result);
    }

    private void helper(int start, int end, List<String> result) {
        if (start < end) {
            result.add(start +"-" + end);
        } else if (start == end) {
            result.add(Integer.toString(start));
        }
    }

    public static void main(String[] args) {
        AbbrevRanges sol = new AbbrevRanges();
        System.out.println(sol.abbrev(new int[]{2, 3, 5, 6, 7,14}));
        System.out.println(sol.abbrev(new int[]{1, 2, 3, 5, 6, 7, 14}));
        System.out.println(sol.abbrev(new int[]{1, 2, 3, 5, 6, 7, 14, 99}));
        System.out.println(sol.abbrev(new int[]{}));
    }
}
