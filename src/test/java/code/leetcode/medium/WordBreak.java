package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class WordBreak {
    private Trie dict = new Trie();
    private HashMap<String, Boolean> cache = new HashMap<>();

    public boolean wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            dict.insert(w);
        }
        return breakString(s);
    }

    private boolean breakString(String s) {
        if (s.isEmpty()) {
            return true;
        }
        Boolean res = cache.get(s);
        if (res == null) {
            res = false;
            List<Integer> words = dict.findWords(s);
            for (int i = words.size() - 1; i >= 0; i--) {
                if (breakString(s.substring(words.get(i)))) {
                    res = true;
                    break;
                }
            }
            cache.put(s, res);
        }
        return res;
    }

    public static class Trie {
        private static class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;

            public TrieNode() {
                this.children = new TrieNode[26]; // Assuming lowercase English letters only
                this.isEndOfWord = false;
            }
        }

        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode current = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        public List<Integer> findWords(String text) {
            List<Integer> words = new ArrayList<>();
            TrieNode current = root;
            for (int i = 0; i < text.length(); i++) {
                int index = text.charAt(i) - 'a';
                if (current.children[index] == null) {
                    break;
                }
                current = current.children[index];
                if (current.isEndOfWord) {
                    words.add(i + 1);
                }
            }
            return words;
        }
    }

    @Test
    void testTrie() {
        dict.insert("e");
        dict.insert("abc");
        dict.insert("abcde");
        assertEquals(List.of(3, 5), dict.findWords("abcdexyz"));
        assertEquals(List.of(1), dict.findWords("e"));
    }

    @Test
    void test() {
        assertEquals(true, wordBreak("leetcode", List.of("leet", "code")));
    }

    @Test
    void test2() {
        assertEquals(false, wordBreak("catsandog", List.of("cats", "dog", "sand", "and", "cat")));
    }

    @Test
    void test3() {
        assertEquals(true, wordBreak("bb", List.of("a", "b", "bbb", "bbbb")));
    }

    @Test
    void test4() {
        assertEquals(true, wordBreak("abcd", List.of("a", "abc", "b", "cd")));
    }

    @Test
    void test5() {
        assertEquals(true, wordBreak("aaaaaaa", List.of("aaaa", "aaa")));
    }

    @Test
    void test6() {
        assertEquals(false, wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                List.of("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }
}
