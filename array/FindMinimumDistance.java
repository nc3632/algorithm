/**
 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y in arr[]. The
 * array might also contain duplicates. You may assume that both x and y are different and present in arr[].
 *
 * Examples:
 * Input: arr[] = {1, 2}, x = 1, y = 2
 * Output: Minimum distance between 1 and 2 is 1.
 *
 * Input: arr[] = {3, 4, 5}, x = 3, y = 5
 * Output: Minimum distance between 3 and 5 is 2.
 *
 * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
 * Output: Minimum distance between 3 and 6 is 4.
 *
 * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
 * Output: Minimum distance between 3 and 2 is 1.
 */

public class FindMinimumDistance {
    public int minDist(int arr[], int x, int y) {
        int min = Integer.MAX_VALUE;

        int prev = 0, prevIdx = 0, i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] == x || arr[i] == y) {
                prev = arr[i];
                prevIdx = i;
                break;
            }
        }

        i++;
        for (; i < arr.length; i++) {
            if (arr[i] == x || arr[i] == y) {
                if (arr[i] == prev) {
                    prevIdx = i;
                } else {
                    min = Math.min(min, i - prevIdx);
                    prev = arr[i];
                    prevIdx = i;
                }
            }
        }

        return min;
    }

    public static void main(String[] args) {
        FindMinimumDistance sol = new FindMinimumDistance();
        System.out.println(sol.minDist(new int[]{1, 2}, 1, 2));
        System.out.println(sol.minDist(new int[]{3, 4, 5}, 3, 5));
        System.out.println(sol.minDist(new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, 3, 6));
        System.out.println(sol.minDist(new int[]{2, 5, 3, 5, 4, 4, 2, 3}, 3, 2));
    }
}
