package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class WordBreak2 {
    private Trie dict = new Trie();
    private List<String> res = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        for (String w : wordDict) {
            dict.insert(w);
        }
        breakString(s, new ArrayList<>());
        return res;
    }

    private void breakString(String s, List<String> words) {
//        System.out.println("." + s + ".");
        if (s.isEmpty()) {
            res.add(String.join(" ", words));
//            System.out.println("[" + res.get(res.size() - 1) + "]");
        }
        List<Integer> breaks = dict.findWords(s);
        for (int i : breaks) {
            String w = s.substring(0, i);
            String suffix = s.substring(i);
            words.add(w);
            breakString(suffix, words);
            words.remove(words.size() - 1);
        }
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
    void test2() {
        assertEquals(2, wordBreak("catsanddog", List.of("cats", "dog", "sand", "and", "cat")).size());
    }

    @Test
    void test3() {
        assertEquals(3, wordBreak("pineapplepenapple", List.of("apple","pen","applepen","pine","pineapple")).size());
    }


}
