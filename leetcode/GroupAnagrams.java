package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groupings = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            char[] chars = str.toCharArray();
            for (char c : chars) {
                counts[c - 'a']++;
            }
            // convert count map to a string representation that we can hash on
            StringBuilder sb = new StringBuilder();
            for (int count : counts) {
                sb.append(count);
                sb.append('#');
            }
            // insert into map
            String key = sb.toString();
            List<String> anagrams = groupings.getOrDefault(key, new ArrayList<>());
            anagrams.add(str);
            groupings.put(key, anagrams);
        }
        List<List<String>> result = new ArrayList<>();
        groupings.keySet().forEach(key -> result.add(groupings.get(key)));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new GroupAnagrams().groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
