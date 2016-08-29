/**
 * The definition of word square is that for any k (0 <= k <n), row k and col k are exactly the same.
 *
 * For example,
 * A B C D
 * B N R T
 * C R M Y
 * D T Y E
 *
 * and
 *
 * A B C D
 * B N R T
 * C R M
 * D T
 */

public class WordSquare {
    public boolean isWordSquare(char[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr[i].length; j++) {
                if (arr[i][j] != arr[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        WordSquare sol = new WordSquare();

        char[][] arr = new char[][] {
                {'A', 'B', 'C', 'D'},
                {'B', 'N', 'R', 'T'},
                {'C', 'R', 'M'},
                {'D', 'T'}
        };
        System.out.println(sol.isWordSquare(arr));

        arr = new char[][] {
                {'A', 'B', 'C', 'D'},
                {'B', 'N', 'R', 'T'},
                {'C', 'R', 'M', 'Y'},
                {'D', 'T', 'Y', 'E'}
        };
        System.out.println(sol.isWordSquare(arr));
    }
}
