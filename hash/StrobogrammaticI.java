import java.util.HashMap;
import java.util.Map;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 *
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */

public class StrobogrammaticI {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('0', '0');
            put('1', '1');
            put('6', '9');
            put('8', '8');
            put('9', '6');
        }};

        int start = 0, end = num.length() - 1;
        while (start < end) {
            if (!map.containsKey(num.charAt(start)) || map.get(num.charAt(start)) != num.charAt(end)) {
                return false;
            }

            start++;
            end--;
        }

        return true;
    }

    public static void main(String[] args) {
        StrobogrammaticI sol = new StrobogrammaticI();
        System.out.println(sol.isStrobogrammatic("69"));
        System.out.println(sol.isStrobogrammatic("88"));
        System.out.println(sol.isStrobogrammatic("818"));
        System.out.println(sol.isStrobogrammatic("62"));
    }
}
