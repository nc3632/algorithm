/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and
 * return the shortest palindrome you can find by performing this transformation.
 *
 * For example:
 *
 * Given "aacecaaa", return "aaacecaaa".
 *
 * Given "abcd", return "dcbabcd".
 */

public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        int end = s.length() - 1;
        for (; end > 0; end--) {
            if (isPalindrome(s, 0, end)) {
                break;
            }
        }

        String extra = s.substring(end + 1);
        return new StringBuilder(extra).reverse().toString() + s;
    }

    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ShortestPalindrome sol = new ShortestPalindrome();
        System.out.println(sol.shortestPalindrome("aacecaaa"));
        System.out.println(sol.shortestPalindrome("abcd"));
    }
}
