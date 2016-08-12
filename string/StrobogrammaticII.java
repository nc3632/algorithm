import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Find all strobogrammatic numbers that are of length = n.
 *
 * For example,
 * Given n = 2, return ["11","69","88","96"].
 *
 * Hint:
 *
 * Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */

public class StrobogrammaticII {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private List<String> helper(int n, int totalLength) {
        if (n == 0) {
            return totalLength == 0 ? new ArrayList<>() : Arrays.asList("");
        } else if (n == 1) {
            return Arrays.asList("0", "1", "8");
        } else {
            List<String> result = new ArrayList<>();
            List<String> temp = helper(n - 2, totalLength);
            for (String s : temp) {
                result.add("1" + s + "1");
                result.add("6" + s + "9");
                result.add("8" + s + "8");
                result.add("9" + s + "6");

                if (n != totalLength) {
                    result.add("0" + s + "0");
                }
            }
            return result;
        }
    }

    public List<String> findStrobogrammaticNonRecursive(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }

        List<String> result;
        if ((n & 1) == 1) {
            result = Arrays.asList("0", "1", "8");
        } else {
            result = Arrays.asList("");
        }

        for (int i = 0; i < n / 2; i++) {
            List<String> temp = result;
            result = new ArrayList<>();

            for (String s: temp) {
                result.add("1" + s + "1");
                result.add("6" + s + "9");
                result.add("8" + s + "8");
                result.add("9" + s + "6");

                if (i != n / 2 - 1) {
                    result.add("0" + s + "0");
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        StrobogrammaticII sol = new StrobogrammaticII();

        System.out.println(sol.findStrobogrammatic(0));
        System.out.println(sol.findStrobogrammaticNonRecursive(0));
        System.out.println(sol.findStrobogrammatic(1));
        System.out.println(sol.findStrobogrammaticNonRecursive(1));
        System.out.println(sol.findStrobogrammatic(2));
        System.out.println(sol.findStrobogrammaticNonRecursive(2));
        System.out.println(sol.findStrobogrammatic(3));
        System.out.println(sol.findStrobogrammaticNonRecursive(3));
        System.out.println(sol.findStrobogrammatic(4));
        System.out.println(sol.findStrobogrammaticNonRecursive(4));
    }
}
