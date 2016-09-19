import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once
 * and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example:
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"
 */

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> countMap = new HashMap<>();
        Map<Character, Boolean> visitedMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
            visitedMap.put(ch, false);
        }

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            countMap.put(ch, countMap.get(ch) - 1);

            if (visitedMap.get(ch)) {
                // If the character appeared before, ignore it since the goal is to remove duplicates
                continue;
            }

            while (!stack.isEmpty() && ch < stack.peek() && countMap.get(stack.peek()) > 0) {
                // If there are bigger ones in the stack than the current character,
                // pop out them if these bigger ones will appear again.
                // Make it as unvisited since we want to save the new ones into the stack.
                visitedMap.put(stack.pop(), false);
            }
            stack.push(ch);
            visitedMap.put(ch, true);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters sol = new RemoveDuplicateLetters();
        System.out.println(sol.removeDuplicateLetters("bcabc"));
        System.out.println(sol.removeDuplicateLetters("cbacdcbc"));
    }
}
