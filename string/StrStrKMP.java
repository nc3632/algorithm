/**
 * Implement strStr().
 *
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

public class StrStrKMP {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }

        int[] maxPrefixSuffixArray = findLongestPrefixSuffixArray(needle);

        for (int i = 0, j = 0; i < haystack.length() && j < needle.length(); ) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                if (j == needle.length()) {
                    return i - j;
                }
            } else {
                if (j == 0) {
                    i++;
                } else {
                    j = maxPrefixSuffixArray[j - 1];
                }
            }
        }

        return -1;
    }

    private int[] findLongestPrefixSuffixArray(String str) {
        // Find the longest prefix of needle[0..i-1], which is also the suffix of needle[1..i]
        int[] maxPrefixSuffixArray = new int[str.length()];
        int currMaxLen = 0;
        for (int i = 1; i < str.length();) {
            if (str.charAt(i) == str.charAt(currMaxLen)) {
                currMaxLen++;
                maxPrefixSuffixArray[i] = currMaxLen;
                i++;
            } else {
                if (currMaxLen != 0) {
                    currMaxLen = maxPrefixSuffixArray[currMaxLen - 1];
                } else {
                    currMaxLen = 0;
                    maxPrefixSuffixArray[i] = currMaxLen;
                    i++;
                }
            }
        }

        return maxPrefixSuffixArray;
    }

    public static void main(String[] args) {
        StrStrKMP sol = new StrStrKMP();
        System.out.println(sol.strStr("a", ""));
    }
}
