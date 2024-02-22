package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class WordDictionary {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord;
    }

    private TrieNode root;

    void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode child = current.children.get(ch);
            if (child == null) {
                child = new TrieNode();
                current.children.put(ch, child);
            }
            current = child;
        }
        current.isEndOfWord = true;
    }

    boolean search(TrieNode current, String word, int i) {
        if (i == word.length()) {
            return current != null && current.isEndOfWord;
        }

        char ch = word.charAt(i);
        if (ch == '.') {
            for (TrieNode child : current.children.values()) {
                if (search(child, word, i + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            var child = current.children.get(ch);
            if (child == null) {
                return false;
            } else {
                return search(child, word, i + 1);
            }
        }
    }

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        insert(word);
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }
}

public class WordDictionaryTest {

    WordDictionary dictionary = new WordDictionary();

    @Test
    void test() {
        dictionary.addWord("bad");
        dictionary.addWord("dad");
        dictionary.addWord("mam");

        assertEquals(true, dictionary.search("bad"));
        assertEquals(true, dictionary.search(".ad"));
        assertEquals(true, dictionary.search("ba."));
        assertEquals(true, dictionary.search("m.m"));
    }
}
