package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

/*
1400 ms
 */
class WordLadder {
    int end = -1;
    int begin = -1;
    List<List<Integer>> transformations = new ArrayList<>();

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        transform(beginWord, endWord, wordList);
        if (end == -1) {
            return 0;
        }
        return BFS();
    }

    private int BFS() {
        Deque<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(begin);
        queue.offer(new int[] {begin, 1});

        while (!queue.isEmpty()) {
            int[] element = queue.pollFirst();
            int from = element[0];
            int stepCount = element[1];
            if (from == end) {
                return stepCount;
            }

            stepCount++;
            for (int next : transformations.get(from)) {
                if (!visited.contains(next)) {
//                    System.out.println(stepCount + " " + from + "->" + next);
                    visited.add(next);
                    queue.offer(new int[] {next, stepCount});
                }
            }

        }

        // If we've exhausted all options and haven't reached the end, return -1.
        return 0;
    }

    private void transform(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(beginWord)) {
            wordList = new ArrayList<>(wordList);
            wordList.add(0, beginWord);
        }
        for (int i = 0; i < wordList.size(); i++) {
            if (endWord.equals(wordList.get(i))) {
                end = i;
            } else if (beginWord.equals(wordList.get(i))) {
                begin = i;
            }
            List<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < wordList.size(); j++) {
                if (i != j && isMatch(wordList.get(i), wordList.get(j))) {
                    tmp.add(j);
                }
            }
            transformations.add(tmp);
        }
    }

    private boolean isMatch(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    void test() {
        assertEquals(5, ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
    }

    @Test
    void test2() {
        assertEquals(3, ladderLength("hot", "dog", Arrays.asList("hot","dog","cog","pot","dot")));
    }

    @Test
    void test3() {
        assertEquals(5, ladderLength("hit", "cog", Arrays.asList("hot","cog","dot","dog","hit","lot","log")));
    }

    @Test
    void test4() {
        assertEquals(10, ladderLength("ymain", "oecij", Arrays.asList(
                "ymann","yycrj","oecij","ymcnj","yzcrj","yycij",
                "xecij","yecij","ymanj","yzcnj","ymain")));
    }

    @Test
    void test5() {
        assertEquals(5, ladderLength("qa", "sq", Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr",
                "ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po",
                "fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge",
                "th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me",
                "mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa",
                "he","lr","sq","ye")));
    }
}
