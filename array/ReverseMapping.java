import java.util.Arrays;

/**
 * The input is a character array and an integer array.
 * The input character array was the result of converting another
 * character array according to this input integer array.
 *
 * The values of this integer array represent the index of the
 * character originally at this spot.
 *
 * For example, character array: ["cat", "rabbit", "dog", "mouse"],
 * integer array: [2, 0, 3, 1]
 * and the output is ["dog", "cat", "mouse", "rabbit"' */

public class ReverseMapping {
    public void convert(String[] strs, int[] ary) {
        if (ary == null || ary.length == 0) {
            return;
        }

        String first = strs[0];
        int source = ary[0], dest = 0;
        do {
            strs[dest] = strs[source];
            dest = source;
            source = ary[dest];
        } while (source != 0);

        strs[dest] = first;
    }

    public static void main(String[] args) {
        ReverseMapping sol = new ReverseMapping();

        String[] strs = new String[]{"cat", "rabbit", "dog", "mouse"};
        int[] ary = new int[]{2, 0, 3, 1};
        sol.convert(strs, ary);

        System.out.println(Arrays.toString(strs));
    }
}
