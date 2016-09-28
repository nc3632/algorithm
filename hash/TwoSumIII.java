import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a TwoSum class. It should support the following operations:add and find.
 *
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * For example,
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 */

public class TwoSumIII {
    private Map<Integer, Integer> map = new HashMap<>();

    public void add(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    public boolean find(int num) {
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (map.containsKey(num - entry.getKey()) &&
                    (entry.getKey() != num - entry.getKey() || entry.getValue() > 1)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        TwoSumIII sol = new TwoSumIII();
        sol.add(1);
        sol.add(3);
        sol.add(5);

        System.out.println(sol.find(4));
        System.out.println(sol.find(7));
    }
}
