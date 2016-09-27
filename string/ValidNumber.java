/**
 * Validate if a given string is numeric.
 *
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before
 * implementing one.
 */
public class ValidNumber {
    public boolean isNumber(String s) {
        int[][] machine = new int[][] {
                {2, 1, -1, 3, 0, -1},       // Initial state
                {2, -1, -1, 3, -1, -1},     // Sign at the beginning
                {2, -1, 5, 4, 8, -1},       // Digit before 'e' or '.'
                {4, -1, -1, -1, -1, -1},    // '.' before anything
                {4, -1, 5, -1, 8, -1},      // '.' after digits
                {7, 6, -1, -1, -1, -1},     // 'e' appears
                {7, -1, -1, -1, -1, -1},    // sign after 'e'
                {7, -1, -1, -1, 8, -1},     // digits after 'e'
                {-1, -1, -1, -1, 8, -1}     // blank
        };

        boolean[] endingStateValidity = new boolean[] {false, false, true, false, true, false, false, true, true,
                true, true};

        int state = 0;
        for (int i = 0; i < s.length(); i++) {
            state = machine[state][getType(s.charAt(i))];

            if (state == -1) {
                return false;
            }
        }

        return endingStateValidity[state];
    }

    private int getType(char ch) {
        if (Character.isDigit(ch)) {
            return 0;
        } else if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == 'e') {
            return 2;
        } else if (ch == '.') {
            return 3;
        } else if (ch == ' ') {
            return 4;
        } else {
            return 5;
        }
    }

    public static void main(String[] args) {
        ValidNumber sol = new ValidNumber();
        System.out.println(sol.isNumber("0"));
        System.out.println(sol.isNumber(" 0.1 "));
        System.out.println(sol.isNumber("abc"));
        System.out.println(sol.isNumber("1 a"));
        System.out.println(sol.isNumber("2e10"));
        System.out.println(sol.isNumber("-1."));
    }
}
