package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import code.utils.Trie;

public class StreamCheckerTest {
    static class StreamChecker {
        char[] text = new char[10_000];
        int size = 0;

        private static class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord;

            TrieNode() {
                children = new TrieNode[26]; // Assuming only lowercase English letters
                isEndOfWord = false;
            }
        }

        private TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode current = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        boolean search() {
            TrieNode current = root;
            for (int i = size - 1; i >= 0; i--) {
                char ch = text[i];
                int index = ch - 'a';
                if (current.children[index] == null) {
                    return false;
                }
                current = current.children[index];
                if (current.isEndOfWord) {
                    return true;
                }
            }
            return false;
        }

        public StreamChecker(String[] words) {
            for(String word : words) {
                insert(word);
            }
        }

        public boolean query(char letter) {
            text[size++] = letter;
            return search();
        }
    }

    @Test
    void test() {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});

        assertEquals(false, streamChecker.query('a')); // return False
        assertEquals(false, streamChecker.query('b')); // return False
        assertEquals(false, streamChecker.query('c')); // return False
        assertEquals(true, streamChecker.query('d')); // return True, because 'cd' is in the wordlist
        assertEquals(false, streamChecker.query('e')); // return False
        assertEquals(true, streamChecker.query('f')); // return True, because 'f' is in the wordlist
        assertEquals(false, streamChecker.query('g')); // return False
        assertEquals(false, streamChecker.query('h')); // return False
        assertEquals(false, streamChecker.query('k')); // return False
        assertEquals(true, streamChecker.query('l')); // return True, because 'kl' is in the wordlist
    }
}
