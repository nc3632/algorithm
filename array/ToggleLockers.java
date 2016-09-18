import java.util.ArrayList;
import java.util.List;

/**
 * Suppose you're in a hallway lined with 100 closed lockers.
 * You begin by opening every locker. Then you close every second locker. Then you go to every third locker and open
 * it (if it's closed) or close it (if it's open). Let's call this action toggling a locker. Continue toggling every
 * nth locker on pass number n. After 100 passes, where you toggle only locker #100, how many lockers are open?
 */

public class ToggleLockers {
    public List<Integer> openLockers(int n) {
        int[] count = new int[100];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 100; j++) {
                if ((j % i) == 0) {
                    count[j - 1]++;
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= count.length; i++) {
            if (count[i - 1] % 2 != 0) {
                result.add(i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ToggleLockers sol = new ToggleLockers();
        System.out.println(sol.openLockers(100));
    }
}
