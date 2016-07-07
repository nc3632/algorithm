/**
 * Implement pow(x, n).
 */

public class Pow {
    public double myPow(double x, int n) {
        // To avoid overflow
        long m = (long) ((n >= 0) ? n : -n);
        double result = 1;
        while (m > 0) {
            // Using 7 (i.e., 111) as an example
            // 111 = 100 + 10 + 1
            // For 100, we need x^4
            // For 10, we need x^2
            // For 1, we need x
            // that is why when we double up the value of x,
            // we need to put the temporary result to result
            if ((m & 1) == 1) {
                result *= x;
            }

            x *= x;
            m >>= 1;
        }

        return n >= 0 ? result : 1 / result;
    }

    public double myPowRecursive(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return myPowRecursive(x, -n);
        }

        double result = myPowRecursive(x, n / 2);

        if ((n & 1) == 0) {
            return result * result;
        } else {
            return x * result * result;
        }
    }
}
