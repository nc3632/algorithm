/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 *
 * For example:
 * Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 */

import java.util.ArrayList;
import java.util.List;

public class RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        restoreIpAddresses(s, 1, 0, result, sb);

        return result;
    }

    private void restoreIpAddresses(String s, int segment, int startPos, List<String> result, StringBuilder sb) {
        if (segment == 5) {
            if (startPos >= s.length()) {
                result.add(sb.substring(0, sb.length() - 1));
            }
            return;
        }

        for (int endPos = startPos + 1; endPos <= s.length(); endPos++) {
            String candidate = s.substring(startPos, endPos);
            if (candidate.startsWith("0") && candidate.length() > 1
                    || Integer.valueOf(s.substring(startPos, endPos)) > 255) {
                break;
            }

            sb.append(candidate + ".");
            restoreIpAddresses(s, segment + 1, endPos, result, sb);
            sb.delete(sb.length() - endPos + startPos - 1, sb.length());
        }
    }
}
