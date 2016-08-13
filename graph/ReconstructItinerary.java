import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct
 * the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin
 * with JFK.
 *
 * Note:
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when
 * read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
 * Example 2:
 * tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
 * Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 */

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        for (int i = 0; i < tickets.length; i++) {
            if (!graph.containsKey(tickets[i][0])) {
                graph.put(tickets[i][0], new ArrayList<>());
            }
            graph.get(tickets[i][0]).add(tickets[i][1]);
        }

        graph.values().forEach(valueList -> Collections.sort(valueList));

        List<String> result = new ArrayList<>();
        helper(graph, "JFK", result);

        return result;
    }

    private void helper(Map<String, List<String>> graph, String start, List<String> result) {
        while (graph.containsKey(start) && !graph.get(start).isEmpty()) {
            String city = graph.get(start).remove(0);
            helper(graph, city, result);
        }
        result.add(0, start);
    }
}
