/**
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the
 * list.
 *
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

public class ShortestWordsDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        int shortest = Integer.MAX_VALUE;
        int p1 = -1, p2 = -1;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                p1 = i;
            }

            if (words[i].equals(word2)) {
                p2 = i;
            }

            if (p1 != -1 && p2 != -1) {
                shortest = Math.min(shortest, Math.abs(p1 - p2));
            }
        }

        return shortest;
    }

    public static void main(String[] args) {
        ShortestWordsDistance sol = new ShortestWordsDistance();
        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
        System.out.println(sol.shortestDistance(words, "coding", "practice"));
        System.out.println(sol.shortestDistance(words, "makes", "coding"));
    }
}
