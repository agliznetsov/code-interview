package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<List<String>> lines = splitLines(words, maxWidth);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i < lines.size() - 1 && lines.get(i).size() > 1) {
                res.add(justifyLine(lines.get(i), maxWidth));
            } else {
                res.add(justifyLastLine(lines.get(i), maxWidth));
            }
        }
        return res;
    }

    private String justifyLastLine(List<String> line, int maxWidth) {
        String res = String.join(" ", line);
        if (res.length() < maxWidth) {
            res += " ".repeat(maxWidth - res.length());
        }
        return res;
    }

    private String justifyLine(List<String> line, int maxWidth) {
        int spaceCount = line.size() - 1;
        int stringLen = line.stream().mapToInt(String::length).sum();
        int spaceLen = maxWidth - stringLen;
        int spaceSize = spaceLen / spaceCount;
        int addSpaces = spaceLen % spaceCount;
        int i = 1;
        int n = 0;
        while (i < line.size()) {
            int size = n < addSpaces ? spaceSize + 1 : spaceSize;
            line.add(i, " ".repeat(size));
            i += 2;
            n++;
        }
        return String.join("", line);
    }

    private List<List<String>> splitLines(String[] words, int maxWidth) {
        List<List<String>> res = new ArrayList<>();
        List<String> line = new ArrayList<>();
        int len = 0;
        for (String w : words) {
            int add = len == 0 ? w.length() : w.length() + 1;
            if (len + add <= maxWidth) {
                line.add(w);
                len += add;
            } else {
                res.add(line);
                line = new ArrayList<>();
                line.add(w);
                len = w.length();
            }
        }
        if (len > 0) {
            res.add(line);
        }
        return res;
    }

    @Test
    void test() {
        assertEquals(List.of("This    is    an",
                        "example  of text",
                        "justification.  "),
                fullJustify(new String[] {"This", "is", "an", "example", "of", "text", "justification."}, 16));
    }

    @Test
    void test2() {
        assertEquals(List.of(
                        "Science  is  what we",
                        "understand      well",
                        "enough to explain to",
                        "a  computer.  Art is",
                        "everything  else  we",
                        "do                  "
                ),
                fullJustify(
                        new String[] {"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"},
                        20));
    }
}
