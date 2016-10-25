/**
 * Given two sparse Vectors, compute the Dot Product.
 * Input Format : The first line will contain two numbers(k and n), which are the number of entries for the two
 * vectors respectively.
 * The next k lines are the entries for the first vector, of the form : x y
 * where x is the position and y is the value at that position in the vector.
 * The n lines are the entries of the second vector.
 * Any entries not specified indicate zero at that position.
 * The two vectors will always be of the same length
 *
 * Example input:
 * 3 3
 * 1 4
 * 4 2
 * 5 3
 * 1 7
 * 2 6
 * 5 1
 *
 * Sample Answer: Dot Product = 4*7+3*1 = 31 (only print 31)
 */

public class VectorDot {
    public int calcProduct(int[][] vec1, int[][] vec2) {
        int size1 = vec1.length;
        int size2 = vec2.length;
        int result = 0;
        for (int i = 0, j = 0; i < size1 && j < size2; ) {
            if (vec1[i][0] == vec2[j][0]) {
                result += vec1[i][1] * vec2[j][1];
                i++;
                j++;
            } else if (vec1[i][0] < vec2[j][0]) {
                i++;
            } else {
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        VectorDot sol = new VectorDot();
        System.out.println(sol.calcProduct(new int[][] {{1, 4}, {4, 2}, {5, 3}}, new int[][] {{1, 7}, {2, 6}, {5, 1}}));
    }
}
