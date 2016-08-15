/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 * Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
 *
 * For example,
 * Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
 *
 * Note:
 * Because the range might be a large number, the low and high numbers are represented as string.
 */
public class StrobogrammaticIII {
    private int count;

    public int strobogrammaticInRange(String low, String high) {
        find(low, high, "");
        find(low, high, "0");
        find(low, high, "1");
        find(low, high, "8");

        return count;
    }

    private void find(String low, String high, String s) {
        if (s.length() >= high.length()
                || s.length() == high.length() && s.compareTo(high) > 0) {
            return;
        }

        if ((s.length() > low.length() || s.length() == low.length() && s.compareTo(low) > 0)
                && !(s.length() == 1 && s.charAt(0) == '0')) {
            count++;
        }

        if (!s.isEmpty()) {
            find(low, high, "0" + s + "0");
        }
        find(low, high, "1" + s + "1");
        find(low, high, "6" + s + "9");
        find(low, high, "8" + s + "8");
        find(low, high, "9" + s + "6");
    }

    public static void main(String[] args) {
        StrobogrammaticIII sol = new StrobogrammaticIII();
        System.out.println(sol.strobogrammaticInRange("50", "100"));
    }
}
