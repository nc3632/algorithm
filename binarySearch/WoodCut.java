/**
 * Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have
 * equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of
 * wood? Given L & k, return the maximum length of the small pieces.
 */

public class WoodCut {
    public int woodCut(int[] L, int k) {
        int start = 1, end = Integer.MAX_VALUE;
        int result = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for (int n : L) {
                count += n / mid;
            }

            if (count >= k) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }
}
