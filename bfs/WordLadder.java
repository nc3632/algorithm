import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 *
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */
public class WordLadder {
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int len = 2;
        while (!queue.isEmpty()) {
            int numConnections = queue.size();
            for (int i = 0; i < numConnections; i++) {
                String word = queue.poll();
                char[] wordChars = word.toCharArray();

                for (int j = 0; j < wordChars.length; j++) {
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        char originalChar = wordChars[j];
                        wordChars[j] = ch;
                        String candidate = new String(wordChars);

                        if (candidate.equals(endWord)) {
                            return len;
                        }

                        if (!candidate.equals(word) && wordList.contains(candidate)) {
                            queue.add(candidate);
                            wordList.remove(candidate);
                        }

                        wordChars[j] = originalChar;
                    }
                }

            }

            len++;
        }

        return 0;
    }

    public static void main(String[] args) {
        WordLadder sol = new WordLadder();
        List<String> wordList = Arrays.asList("hot", "dot", "dog");
        System.out.println(sol.ladderLength("hot", "dog", new HashSet<>(wordList)));
    }
}
