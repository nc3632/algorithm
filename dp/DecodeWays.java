/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 */

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int first = 1;
        int second = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 1; i < s.length(); i++) {
            int current = 0;
            if (s.charAt(i) != '0') {
                current += second;
            }

            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) - '0' <= 6) {
                current += first;
            }

            first = second;
            second = current;
        }

        return second;
    }

    public static void main(String[] args) {
        DecodeWays sol = new DecodeWays();
        System.out.println(sol.numDecodings("10"));
    }
}
