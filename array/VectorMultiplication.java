import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Given two sparse Vectors, compute the Dot Product.
 * Input Format : The first line will contain two numbers(k and n), which are the number of entries for the two
 * vectors respectively.
 * The next k lines are the entries for the first vector, of the form : x y
 * where x is the position and y is the value at that position in the vector.
 * The n lines are the entries of the second vector.
 * Any entries not specified indicate zero at that position.
 * The two vectors will always be of the same length
 *
 * Example input:
 * 3 3
 * 1 4
 * 4 2
 * 5 3
 * 1 7
 * 2 6
 * 5 1
 *
 * Sample Answer: Dot Product = 4*7+3*1 = 31 (only print 31)
 */

public class VectorMultiplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int n = scanner.nextInt();

        Map<Integer, Integer> v1 = new HashMap<>();
        for (int i = 0; i < k; i++) {
            v1.put(scanner.nextInt(), scanner.nextInt());
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            int row = scanner.nextInt();
            int val = scanner.nextInt();

            if (v1.containsKey(row)) {
                sum += v1.get(row) * val;
            }
        }

        System.out.println(sum);
    }
}
