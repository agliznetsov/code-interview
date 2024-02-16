package code.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class GroupAnagram {
    private Map<String, List<String>> map = new HashMap<>();

    public List<List<String>> groupAnagrams(String[] strs) {
        for(String s : strs) {
            String key = normalize(s);
            map.computeIfAbsent(key, it -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    private String normalize(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return String.valueOf(chars);
    }

    @Test
    void test() {
        System.out.println(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
