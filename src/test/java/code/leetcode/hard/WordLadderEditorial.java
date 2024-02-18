package code.leetcode.hard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;

/**
 * 13 ms bi-directional search, 84 ms simple search
 */
public class WordLadderEditorial {
    private Set<String> wordSet;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Initialize the word set with the given word list
        wordSet = new HashSet<>(wordList);
        // If the endWord is not in the wordList, return 0 as no ladder exists
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        // Use two queues to implement bidirectional BFS
        Queue<String> queueBegin = new ArrayDeque<>();
        Queue<String> queueEnd = new ArrayDeque<>();
        // Maps to keep track of the path lengths from the begin and end words
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        // Initialize the queues and maps
        queueBegin.offer(beginWord);
        queueEnd.offer(endWord);
        visitedBegin.put(beginWord, 1); // The step count starts at 1
        visitedEnd.put(endWord, 1);

        // Perform BFS until one of the queues is empty
        while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
            // Always extend the smaller queue in the current iteration
            int result = queueBegin.size() <= queueEnd.size() ?
                    extendQueue(visitedBegin, visitedEnd, queueBegin) :
                    extendQueue(visitedEnd, visitedBegin, queueEnd);
            // If a connection is found, return the total length of the path
            if (result != -1) {
                return result;
            }
        }
        // If no path is found, return 0
        return 0;
    }

    private int extendQueue(Map<String, Integer> visitedOne, Map<String, Integer> visitedOther, Queue<String> queue) {
        // Process each word in the current layer
        for (int i = queue.size(); i > 0; --i) {
            String currentWord = queue.poll();
            int currentStep = visitedOne.get(currentWord);
            char[] characters = currentWord.toCharArray();
            // Try changing every character to make new words
            for (int j = 0; j < characters.length; ++j) {
                char originalChar = characters[j];
                for (char ch = 'a'; ch <= 'z'; ++ch) {
                    characters[j] = ch;
                    String newWord = new String(characters);
                    // Skip if the new word is not in the word set or already visited
                    if (!wordSet.contains(newWord) || visitedOne.containsKey(newWord)) {
                        continue;
                    }
                    // If the new word is in the other visited map, a path is found
                    if (visitedOther.containsKey(newWord)) {
                        return currentStep + visitedOther.get(newWord);
                    }
                    // Otherwise, add the new word to the queue and visited map
                    queue.offer(newWord);
                    visitedOne.put(newWord, currentStep + 1);
                }
                // Restore the original character
                characters[j] = originalChar;
            }
        }
        // If no progress is made in this direction, return -1
        return -1;
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
