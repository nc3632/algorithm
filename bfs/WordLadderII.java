import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence
 * (s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the word list
 * For example,
 *
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * Return
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 */

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        List<String> candidate = new ArrayList<>();

        wordList.remove(beginWord);
        Map<String, List<String>> mapping = new HashMap<>();
        int len = ladderLength(beginWord, endWord, wordList, mapping);

        List<String> beginWords = new ArrayList<>();
        beginWords.add(beginWord);

        findLadders(beginWords, endWord, len, mapping, candidate, result);

        return result;
    }

    public void findLadders(List<String> beginWords, String endWord, int len,
                            Map<String, List<String>> mapping, List<String> candidate, List<List<String>> result) {

        if (candidate.size() == len && candidate.contains(endWord)) {
            result.add(new ArrayList<>(candidate));
            return;
        }

        if (beginWords == null || beginWords.size() == 0) {
            return;
        }

        for (int i = 0; i < beginWords.size(); i++) {
            String word = beginWords.get(i);

            candidate.add(word);
            findLadders(mapping.get(word), endWord, len, mapping, candidate, result);
            candidate.remove(candidate.size() - 1);
        }
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList, Map<String, List<String>> mapping) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);

        int len = 2;
        while (!queue.isEmpty()) {
            int numConnections = queue.size();
            boolean found = false;
            for (int i = 0; i < numConnections; i++) {
                String word = queue.poll();
                if (find(word, endWord, wordList, mapping)) {
                    found = true;
                }
                if (mapping.containsKey(word)) {
                    queue.addAll(mapping.get(word));
                }
            }

            if (found) {
                return len;
            } else {
                len++;
            }
        }

        return 0;
    }

    public boolean find(String word, String endWord, Set<String> wordList, Map<String, List<String>> mapping) {
        boolean found = false;
        char[] wordChars = word.toCharArray();
        for (int i = 0; i < wordChars.length; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                char originalChar = wordChars[i];
                wordChars[i] = ch;
                String candidate = new String(wordChars);

                if (candidate.equals(endWord)) {
                    found = true;
                }

                if (!word.equals(candidate) && wordList.contains(candidate) || endWord.equals(candidate)) {
                    if (!mapping.containsKey(word)) {
                        mapping.put(word, new ArrayList<>());
                    }
                    mapping.get(word).add(candidate);
                    wordList.remove(candidate);
                }

                wordChars[i] = originalChar;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        WordLadderII sol = new WordLadderII();
        Set<String> wordList = new HashSet<>();
        wordList.addAll(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"));

        List<List<String>> result = sol.findLadders("red", "tex", wordList);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
