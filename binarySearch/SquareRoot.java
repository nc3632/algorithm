/*
 * sqrt(x)
 */

public class SquareRoot {
    private static int TIMES = 10;

    public double mySqrt(int x) {
        for (int i = 1, j = x; i <= j;) {
            int mid = i + (j - i) / 2;
            if (x / mid >= mid && x / (mid + 1) < mid + 1) {
                return mySqrtNewton(x, mid);
            } else if (x / mid < mid) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return 0;
    }

    private double mySqrtNewton(int n, int x0) {
        // x^2 = n, f(x) = x^2 - n, f'(x) = 2x.
        // f(x) = f(x0) + f'(x0)(x - x0) = x0^2 - n + 2x0(x - x0) = -x0^2 - n + 2x0x = 0
        // x = (n + x0^2) / 2x0
        double result = x0;
        for (int i = 0; i < TIMES; i++) {
            result = (n + result * result) / (2.0 * result);
        }

        return result;
    }

    public static void main(String[] args) {
        SquareRoot sol = new SquareRoot();
        System.out.println(sol.mySqrt(10));
    }
}
