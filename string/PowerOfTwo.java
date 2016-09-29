/**
 * A big number if represented with string. Determine if this number is power of 2.
 */

public class PowerOfTwo {
    public boolean isPowerOfTwo(String str) {
        while(!str.equals("1")) {
            StringBuilder sb = new StringBuilder();
            int leftover = 0;
            for (int i = 0; i < str.length(); i++) {
                int num = leftover * 10 + str.charAt(i) - '0';
                int quotient = num / 2;
                if (quotient != 0) {
                    sb.append(quotient);
                }

                leftover = num % 2;
            }

            if (leftover != 0) {
                return false;
            }

            str = sb.toString();
        }

        return true;
    }

    public static void main(String[] args) {
        PowerOfTwo sol = new PowerOfTwo();
        System.out.println(sol.isPowerOfTwo("1024"));
        System.out.println(sol.isPowerOfTwo("1025"));
    }
}
