import java.util.LinkedHashMap;
import java.util.Map;

public class FirstNonRepeated {

    public static void main(String[] args) {
        System.out.println(new FirstNonRepeated().firstUniqChar("loveleetcode"));
    }

    public int firstUniqChar(String s) {

        if (s == null || s.equals("")) {
            return -1;
        }
        Map<Character, Integer> cache = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            System.out.println(s.charAt(i) + ", " + cache.containsKey(s.charAt(i)));
            if (!cache.containsKey(s.charAt(i))) {
                cache.put(s.charAt(i), i);
            } else {
                cache.put(s.charAt(i), -1);
            }
        }
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() >= 0) {
                return entry.getValue();
            }
        }
        return -1;
    }
}
