import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Implement a tiny search engine
 * 1. Build inverted index structure to support full text search
 * 2. Support ignoring stop words
 * 3. Support using stem word
 *
 * What if the size of the index is too big to fit in memory
 * 1. Use a number of nodes, and each one only holds a given number of key words
 * 2. Use consistent hashing to find the corresponding node.
 */

public class TinySearchEngine {
    private static class StemFinder {
        public String getStemWord(String word) {
            word = word.toLowerCase();

            String[] affix = new String[]{"ing", "y"};
            for (String s : affix) {
                if (word.endsWith(s)) {
                    return word.substring(0, word.length() - s.length());
                }
            }

            return word;
        }
    }

    private static class Post {
        String author;
        String content;

        public Post(String author, String content) {
            this.author = author;
            this.content = content;
        }
    }

    private Set<String> stopWords;
    private StemFinder stemFinder = new StemFinder();

    public void setStopWords(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    public Map<String, Map<Post, Integer>> indexPosts(List<Post> posts) {
        Map<String, Map<Post, Integer>> result = new HashMap<>();

        for (Post post : posts) {
            String[] tokens = tokenize(post.content);
            for (String token : tokens) {
                token = stemFinder.getStemWord(token);
                if (!stopWords.contains(token)) {
                    if (!result.containsKey(token)) {
                        result.put(token, new TreeMap<>((Post post1, Post post2) -> post1.author.compareTo
                                (post2.author)));
                    }

                    Map<Post, Integer> count = result.get(token);
                    count.put(post, count.getOrDefault(post, 0) + 1);
                }
            }
        }

        return result;
    }

    private String[] tokenize(String str) {
        return str.split("[., ]+");
    }

    public static void main(String[] args) {
        TinySearchEngine sol = new TinySearchEngine();

        Set stopWords = new HashSet();
        stopWords.add("is");
        stopWords.add("a");
        stopWords.add("it");

        sol.setStopWords(stopWords);

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("author1", "Rainy season is coming"));
        posts.add(new Post("author2", "It has been raining for a week, small rain though"));

        Map<String, Map<Post, Integer>> result = sol.indexPosts(posts);
        for (Map.Entry<String, Map<Post, Integer>> entry : result.entrySet()) {
            System.out.println(entry.getKey());
            Map<Post, Integer> stat = entry.getValue();
            for (Post post : stat.keySet()) {
                System.out.println(post.author + " : " + stat.get(post));
            }
            System.out.println();
        }
    }
}
