import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Implement an iterator that walk through a matrix with the following property
 * 1. Each row is sorted
 * 2. The length of a row is way larger than the number of rows
 *
 * For example, given matrix
 * [
 *    4 8 20
 *    1 6 50
 *    10 11 12
 * ]
 *
 * Keep calling next() would return [1, 4, 6, 8, 10, 11, 12, 20, 50]
 */

public class IterateRowSortedMatrix {
    private static class Element {
        int row;
        int col;
        int val;

        Element(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    private int[][] matrix;
    private PriorityQueue<Element> queue = new PriorityQueue<>(new Comparator<Element>() {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.val - o2.val;
        }
    });

    public IterateRowSortedMatrix(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            queue.offer(new Element(i, 0, matrix[i][0]));
        }
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public Integer next() {
        if (queue.isEmpty()) {
            return null;
        }

        Element elem = queue.poll();
        if (elem.col < matrix[0].length - 1) {
            queue.offer(new Element(elem.row, elem.col + 1, matrix[elem.row][elem.col + 1]));
        }

        return elem.val;
    }

    public static void main(String[] args) {
        IterateRowSortedMatrix sol = new IterateRowSortedMatrix(new int[][] {
                {4, 8, 20},
                {1, 6, 50},
                {10, 11, 12}
        });

        while (sol.hasNext()) {
            System.out.println(sol.next());
        }
    }
}
