import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Given an array of integers and a number k, the majority number is the number that occurs
 * more than 1/k of the size of the array. Find it.
 * Notice that there is only one majority number in the array.
 */
public class MajorityElementIII {
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else if (map.size() < k - 1) {
                map.put(n, 1);
            } else {
                List<Integer> removeList = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getValue() == 1) {
                        removeList.add(entry.getKey());
                    } else {
                        map.put(entry.getKey(), entry.getValue() - 1);
                    }
                }

                for (Integer toRemove : removeList) {
                    map.remove(toRemove);
                }
            }
        }

        for (Integer key : map.keySet()) {
            map.put(key, 0);
        }

        for (int n : nums) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            }
        }

        int votes = 0, candidate = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > votes) {
                candidate = entry.getKey();
                votes = entry.getValue();
            }
        }

        return candidate;
    }

    public static void main(String[] args) {
        MajorityElementIII sol = new MajorityElementIII();
        System.out.println(sol.majorityNumber(new ArrayList<>(Arrays.
                stream(new int[] {3, 1, 2, 3, 2, 3, 3, 4, 4, 4}).boxed().collect(Collectors.toList())), 3));
    }
}
