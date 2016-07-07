/*
 * sqrt(x)
 */

public class SquareRoot {
    public int mySqrt(int x) {
        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (x / mid >= mid && x / (mid + 1) < mid + 1) {
                return mid;
            } else if (x / mid < mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return 0;
    }
}
