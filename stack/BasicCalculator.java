import java.util.Stack;

/**
 * Support +, -, (, )
 */

public class BasicCalculator {
    public int calculate(String s) {
        String delimiters = "(?=[+\\-()])|(?<=[+\\-()])";
        String[] tokens = s.split(delimiters);

        Stack<Integer> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String operator = "+";
        int result = 0;

        for (String token : tokens) {
            token = token.trim();
            if (token.equals("")) {
                continue;
            } else if (token.equals("(")) {
                operands.push(result);
                operators.push(operator);

                result = 0;
                operator = "+";
            } else if (token.equals(")")) {
                result = calc(operators.pop(), operands.pop(), result);
            } else if (token.equals("+") || token.equals("-")) {
                operator = token;
            } else {
                result = calc(operator, result, Integer.parseInt(token));
            }
        }
        return result;
    }

    private int calc(String operator, int operand1, int operand2) {
        if (operator.equals("+")) {
            return operand1 + operand2;
        } else {
            return operand1 - operand2;
        }
    }

    public static void main(String[] args) {
        String str = " 1 + 2  (  3 ) ";
        BasicCalculator sol = new BasicCalculator();
        System.out.println(sol.calculate(str));
    }
}
