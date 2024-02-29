package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Test;

class BrowserHistoryTest {

    class BrowserHistory {
        private Deque<String> backHistory = new ArrayDeque<>();
        private Deque<String> forwardHistory = new ArrayDeque<>();

        private String location;

        public BrowserHistory(String homepage) {
            this.location = homepage;
        }

        public void visit(String url) {
            backHistory.push(location);
            forwardHistory.clear();
            this.location = url;
        }

        public String back(int steps) {
            while (steps > 0 && !backHistory.isEmpty()) {
                forwardHistory.push(location);
                location = backHistory.pop();
                steps--;
            }
            return location;
        }

        public String forward(int steps) {
            while (steps > 0 && !forwardHistory.isEmpty()) {
                backHistory.push(location);
                location = forwardHistory.pop();
                steps--;
            }
            return location;
        }
    }

    @Test
    void test() {
        BrowserHistory browser = new BrowserHistory("A");
        browser.visit("B");
        browser.visit("C");
        browser.visit("D");
        assertEquals("C", browser.back(1));
        assertEquals("B", browser.back(1));
        assertEquals("C", browser.forward(1));
        assertEquals("D", browser.forward(1));

    }
}
