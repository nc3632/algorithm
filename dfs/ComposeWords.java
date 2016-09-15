import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Input1 is a list of words that we want to compose.
 * Input2 is the list of words that are available to compose the words in input1
 * Calculate the number of different ways to do so.
 *
 * For example,
 * Input1 = {CAT, DOG}
 * Input2 = {GAT, DOC, CD, GOAT, BAD, COOL}
 *
 * Output = {{GAT, DOC}, {CD, GOAT}}
 */

public class ComposeWords {
    public List<List<String>> waysToCompose(List<String> target, List<String> source) {
        int numTotalLetters = 0;
        Map<Character, Integer> lettersMap = new HashMap<>();
        for (int i = 0; i < target.size(); i++) {
            char[] chars = target.get(i).toCharArray();
            for (char ch : chars) {
                lettersMap.put(ch, lettersMap.getOrDefault(ch, 0) + 1);
                numTotalLetters++;
            }
        }

        List<List<String>> result = new ArrayList<>();
        List<String> candidate = new ArrayList<>();
        search(lettersMap, source, 0, numTotalLetters, candidate, result);

        return result;
    }

    private void search(Map<Character, Integer> lettersMap, List<String> source, int start, int numTotalLetters,
                        List<String> candidate, List<List<String>> result) {

        if (numTotalLetters == 0) {
            result.add(new ArrayList<>(candidate));
            return;
        }

        if (start >= source.size()) {
            return;
        }

        for (int i = start; i < source.size(); i++) {
            char[] letters = source.get(i).toCharArray();
            boolean found = true;
            for (char ch : letters) {
                if (!lettersMap.containsKey(ch) || lettersMap.get(ch) <= 0) {
                    found = false;
                    break;
                }
            }

            if (found) {
                candidate.add(source.get(i));
                for (char ch : letters) {
                    lettersMap.put(ch, lettersMap.get(ch) - 1);
                }

                search(lettersMap, source, i + 1, numTotalLetters - letters.length, candidate, result);

                candidate.remove(candidate.size() - 1);
                for (char ch : letters) {
                    lettersMap.put(ch, lettersMap.get(ch) + 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        ComposeWords sol = new ComposeWords();
        System.out.println(sol.waysToCompose(Arrays.asList(new String[] {"CAT", "DOG"}),
                Arrays.asList(new String[] {"GAT", "DOC", "CD", "GOAT", "BAD", "COOL"})));
    }
}
