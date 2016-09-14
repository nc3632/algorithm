import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 *  ['o','a','a','n'],
 *  ['e','t','a','e'],
 *  ['i','h','k','r'],
 *  ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 */

public class WordSearchII {
    private static class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(board, words);

        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                search(board, i, j, root, result);
            }
        }

        return result;
    }

    private TrieNode buildTrie(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode currNode = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int idx = ch - 'a';
                if (currNode.children[idx] == null) {
                    currNode.children[idx] = new TrieNode();
                }
                currNode = currNode.children[idx];
            }
            currNode.word = word;
        }

        return root;
    }

    private void search(char[][] board, int startRow, int startCol, TrieNode currNode, List<String> result) {
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null;
        }

        TrieNode nextNode = null;
        if (startRow < 0 || startRow >= board.length || startCol < 0 || startCol >= board[0].length
                || board[startRow][startCol] == '*'
                || (nextNode = currNode.children[board[startRow][startCol] - 'a']) == null) {
            return;
        }

        char origChar = board[startRow][startCol];
        board[startRow][startCol] = '*';

        search(board, startRow - 1, startCol, nextNode, result);
        search(board, startRow, startCol + 1, nextNode, result);
        search(board, startRow + 1, startCol, nextNode, result);
        search(board, startRow, startCol - 1, nextNode, result);

        board[startRow][startCol] = origChar;
    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();
        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = new String[] {"oath","pea","eat","rain"};

        System.out.println(sol.findWords(board, words));
    }
}
