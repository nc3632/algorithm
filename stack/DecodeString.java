import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 *
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 *
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 *
 * Examples:
 *
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

public class DecodeString {
    public String decodeString(String s) {
        Stack<String> result = new Stack<>();
        Stack<Integer> timesStack = new Stack<>();

        String curr = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                result.push(curr);
                curr = "";
            } else if (ch == ']') {
                int count = timesStack.pop();
                StringBuilder sb = new StringBuilder();
                sb.append(result.pop());
                for (int j = 0; j < count; j++) {
                    sb.append(curr);
                }

                curr = sb.toString();
            } else if (ch >= '0' && ch <= '9'){
                int count = 0;
                for (; s.charAt(i) >= '0' && s.charAt(i) <= '9'; i++) {
                    count = count * 10 + Integer.valueOf(s.charAt(i) - '0');
                }
                i--;

                timesStack.push(count);
            } else {
                curr += s.charAt(i);
            }
        }

        return curr;
    }

    public static void main(String[] args) {
        DecodeString sol = new DecodeString();
        System.out.println(sol.decodeString("3[a]2[bc]"));
        System.out.println(sol.decodeString("3[a2[c]]"));
        System.out.println(sol.decodeString("2[abc]3[cd]ef"));
    }
}
