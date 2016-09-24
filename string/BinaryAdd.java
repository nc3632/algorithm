/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */

public class BinaryAdd {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int p1 = a.length() - 1, p2 = b.length() - 1;
        while (p1 >= 0 || p2 >= 0 || carry == 1) {
            int sum = carry;

            if (p1 >= 0) {
                sum += a.charAt(p1) - '0';
            }

            if (p2 >= 0) {
                sum += b.charAt(p2) - '0';
            }

            sb.insert(0, sum % 2);
            carry = sum / 2;

            p1--;
            p2--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryAdd sol = new BinaryAdd();
        System.out.println(sol.addBinary("11", "1"));
    }
}
