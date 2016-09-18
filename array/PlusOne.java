import java.util.Arrays;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 */

public class PlusOne {
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] ret = new int[digits.length + 1];
        ret[0] = 1;

        return ret;
    }

    public static void main(String[] args) {
        PlusOne sol = new PlusOne();
        System.out.println(Arrays.toString(sol.plusOne(new int[]{1, 9, 8})));
        System.out.println(Arrays.toString(sol.plusOne(new int[]{1, 9, 9})));
        System.out.println(Arrays.toString(sol.plusOne(new int[]{1, 8, 9})));
        System.out.println(Arrays.toString(sol.plusOne(new int[]{9, 9, 9})));
    }
}
