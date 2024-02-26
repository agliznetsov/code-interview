package code.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ListNodeTest {
    @Test
    void test() {
        assertEquals("[1,2,3]", ListNode.fromString("1,2,3").print());
    }
}
