import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 *
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 */

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.isEmpty()) {
            return result;
        }

        helper(num, 0, target, 0, 0, "", result);

        return result;
    }

    private void helper(String num, int start, int target, long currVal, long productPart, String candidate,
                        List<String> result) {

        if (start >= num.length()) {
            if (target == currVal) {
                result.add(candidate);
            }
            return;
        }

        for (int i = start; i < num.length(); i++) {
            if (i > start && num.charAt(start) == '0') {
                break;
            }

            long val = Long.parseLong(num.substring(start, i + 1));

            if (start == 0) {
                helper(num, i + 1, target, val, val, num.substring(start, i + 1), result);
            } else {
                helper(num, i + 1, target, currVal + val, val, candidate + "+" + val, result);
                helper(num, i + 1, target, currVal - productPart + productPart * val, productPart * val,
                        candidate + "*" + val, result);
                helper(num, i + 1, target, currVal - val, -val, candidate + "-" + val, result);
            }
        }
    }

    public static void main(String[] args) {
        ExpressionAddOperators sol = new ExpressionAddOperators();
        System.out.println(sol.addOperators("123", 6));
        System.out.println(sol.addOperators("232", 8));
        System.out.println(sol.addOperators("105", 5));
        System.out.println(sol.addOperators("00", 0));
        System.out.println(sol.addOperators("3456237490", 9191));
    }
}
