import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given that integers are read from a data stream. Find median of elements read so for in efficient way. For
 * simplicity assume there are no duplicates. For example, let us consider the stream 5, 15, 1, 3 …
 *
 * After reading 1st element of stream - 5 -> median - 5
 * After reading 2nd element of stream - 5, 15 -> median - 10
 * After reading 3rd element of stream - 5, 15, 1 -> median - 5
 * After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
 * Making it clear, when the input size is odd, we take the middle element of sorted data. If the input size is even,
 * we pick average of middle two elements in sorted stream.
 *
 * Note that output is effective median of integers read from the stream so far.
 */

public class MedianFinder {
    PriorityQueue<Integer> heap1;
    PriorityQueue<Integer> heap2;

    public MedianFinder() {
        Comparator<Integer> myComparotor = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };

        heap1 = new PriorityQueue<>(myComparotor);
        heap2 = new PriorityQueue<>();
    }

    public double getMedian() {
        if (heap1.size() > heap2.size()) {
            return heap1.peek();
        } else if (heap1.size() < heap2.size()) {
            return heap2.peek();
        } else {
            return (heap1.peek() + heap2.peek()) / 2.0;
        }
    }

    public void addNum(int num) {
        if (heap1.isEmpty()) {
            heap1.add(num);
        } else {
            int heap1Max = heap1.peek();
            if (num <= heap1Max) {
                heap1.offer(num);
                if (heap1.size() > heap2.size() + 1) {
                    heap2.offer(heap1.poll());
                }
            } else {
                heap2.offer(num);
                if (heap2.size() > heap1.size() + 1) {
                    heap1.offer(heap2.poll());
                }
            }
        }
    }

    public static void main(String[] args) {
        MedianFinder sol = new MedianFinder();
        sol.addNum(5);
        System.out.println(sol.getMedian());

        sol.addNum(15);
        System.out.println(sol.getMedian());

        sol.addNum(1);
        System.out.println(sol.getMedian());

        sol.addNum(3);
        System.out.println(sol.getMedian());
    }
}
