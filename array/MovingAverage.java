/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */

public class MovingAverage {
    private int[] window;
    private int pos = 0;
    private int total = 0;
    private int numElems = 0;

    public MovingAverage(int size) {
        this.window = new int[size];
    }

    public double next(int val) {
        total = total - window[pos] + val;
        window[pos] = val;
        pos = (pos + 1) % window.length;

        if (numElems < window.length) {
            numElems++;
        }

        return total * 1.0 / numElems;
    }

    public static void main(String[] args) {
        MovingAverage sol = new MovingAverage(3);
        System.out.println(sol.next(1));
        System.out.println(sol.next(10));
        System.out.println(sol.next(3));
        System.out.println(sol.next(5));
    }
}
