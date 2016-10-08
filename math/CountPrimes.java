/**
 * Count the number of prime numbers less than a non-negative number, n.
 * Use Sieve of Eratosthenes algorithm
 */

public class CountPrimes {
    public int countPrimes(int n) {
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (m[i]) {
                continue;
            }

            count++;

            // If i is a prime, then the 2 * i, 3 * i, ... are not primes
            for (int j = i; j < n; j += i) {
                m[j] = true;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        CountPrimes sol = new CountPrimes();
        System.out.println(sol.countPrimes(2));
        System.out.println(sol.countPrimes(4));
        System.out.println(sol.countPrimes(5));
    }
}
