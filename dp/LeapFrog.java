import java.util.Arrays;

/**
 * Leap frog problem.
 *
 * A frog starts with step 1. And then it can decide its next step size by (+1, 0, -1).
 * Determine whether frog can successfully jump over the water.
 *
 * For example, {'F', 'R', 'R', 'R', 'W', 'W', 'R', 'W', 'R', 'W', 'O'}. The frog can
 * 0->1(v=1), then 1->3(v=2), then 3->6(v=3), then 6->8(v=2), and finally 8->10(v=2).
 */

public class LeapFrog {
    public boolean doable(char[] input) {
        boolean[][] dp = new boolean[input.length][input.length + 1];
        Arrays.fill(dp[0], true);

        for (int i = 1; i < input.length; i++) {
            if (input[i] == 'W') {
                continue;
            }

            for (int v = 1; v <= i; v++) {
                dp[i][v] = dp[i - v][v - 1] || dp[i - v][v] || dp[i - v][v + 1];
            }
        }

        for (int v = 1; v < input.length; v++) {
            if (dp[input.length - 1][v]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LeapFrog sol = new LeapFrog();
        System.out.println(sol.doable(new char[]{'F', 'R', 'R', 'R', 'W', 'W', 'R', 'W', 'R', 'W', 'O'}));
    }
}
