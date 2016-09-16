import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Support +, -, *, /
 */

public class BasicCalculatorII {
    public int calculate(String s) {
        String[] tokens = s.split("(?=[+\\-*/])|(?<=[+\\-*/])");

        Deque<Integer> operands = new ArrayDeque<>();
        Deque<String> operators = new ArrayDeque<>();

        String action = "+";
        for (String token : tokens) {
            token = token.trim();

            if (token.equals("+") || token.equals("-")) {
                action = token;
                operators.add(token);
            } else if (token.equals("*") || token.equals("/")) {
                action = token;
            } else {
                if (action.equals("*") || action.equals("/")) {
                    int val = calc(action, operands.removeLast(), Integer.parseInt(token));
                    operands.addLast(val);
                } else {
                    operands.addLast(Integer.parseInt(token));
                }
            }
        }

        int result = operands.removeFirst();
        while (!operators.isEmpty()) {
            result = calc(operators.removeFirst(), result, operands.removeFirst());
        }
        return result;
    }

    private int calc(String action, int op1, int op2) {
        if (action.equals("*")) {
            return op1 * op2;
        } else if (action.equals("/")) {
            return op1 / op2;
        } else if (action.equals("+")) {
            return op1 + op2;
        } else {
            return op1 - op2;
        }
    }

    public static void main(String[] args) {
        BasicCalculatorII sol = new BasicCalculatorII();
        System.out.println(sol.calculate("3 + 2 * 2"));
        System.out.println(sol.calculate("3 / 2"));
        System.out.println(sol.calculate("3 + 5 / 2"));
    }
}
