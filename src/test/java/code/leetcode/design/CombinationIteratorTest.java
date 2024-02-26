package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CombinationIteratorTest {

    class CombinationIterator {
        List<String> combinations = new ArrayList<>();
        int index = 0;
        String characters;
        int length;

        public CombinationIterator(String characters, int combinationLength) {
            this.characters = characters;
            this.length = combinationLength;
            addChars("", 0);
        }

        private void addChars(String prefix, int start) {
            if (prefix.length() == length) {
                combinations.add(prefix);
            } else {
                for (int i = start; i <= characters.length() - (length - prefix.length()); i++) {
                    addChars(prefix + characters.charAt(i), i + 1);
                }
            }
        }

        public String next() {
            return combinations.get(index++);
        }

        public boolean hasNext() {
            return index < combinations.size();
        }
    }


    @Test
    void test() {
        CombinationIterator iterator = new CombinationIterator("abcde", 3);
        String str = "";
        while (iterator.hasNext()) {
            str += iterator.next() + " ";
        }
        assertEquals("abc abd abe acd ace ade bcd bce bde cde ", str);
    }
}
