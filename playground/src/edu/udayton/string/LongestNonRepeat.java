package edu.udayton.string;

import java.util.HashMap;
import java.util.Map;

public class LongestNonRepeat {

    public static void main(String[] args) {
        String s = "qwerrtyuuiopf";
        System.out.println(new LongestNonRepeat().lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstringSolution(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        int len = 0;
        Map<Character, Character> repeat = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (repeat.containsKey(s.charAt(i))) {
                repeat.clear();
                maxLen = maxLen < len ? len : maxLen;
                len = 1;
                repeat.put(s.charAt(i), s.charAt(i));
            } else {
                len++;
                repeat.put(s.charAt(i), s.charAt(i));
            }
        }
        maxLen = maxLen < len ? len : maxLen;
        return maxLen;
    }
}
