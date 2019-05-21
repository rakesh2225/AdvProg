package edu.udayton.string;

import java.util.*;

public class Anagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length <= 0) {
            return null;
        }
        Map<String, List<String>> anagramGroup = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            Arrays.sort(str.toCharArray());
            System.out.println(str);
            if (anagramGroup.containsKey(str)) {
                anagramGroup.get(str).add(strs[i]);
            } else {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                anagramGroup.put(str, list);
            }
        }
        return new ArrayList<>(anagramGroup.values());
    }
    /*
    Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.
    */

}
