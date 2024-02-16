package code.leetcode.medium;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LRUCacheProblem {

    static class LRUCache {

        private static class Node {
            int key;
            int value;
            Node prev;
            Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public Map<Integer,Node> map;
        private int capacity;
        private Node first;
        private Node last;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) {
                return -1;
            } else {
                updateUsage(node);
                return node.value;
            }
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node == null) {
                if (map.size() == capacity) {
                    map.remove(last.key);
                    unlink(last);
                }
                node = new Node(key, value);
                map.put(key, node);
                linkFirst(node);
            } else {
                node.value = value;
                updateUsage(node);
            }
        }

        private void updateUsage(Node node) {
            unlink(node);
            linkFirst(node);
        }

        private void linkFirst(Node newNode) {
            final Node f = first;
            newNode.prev = null;
            newNode.next = f;
            first = newNode;
            if (f == null) {
                last = newNode;
            } else {
                f.prev = newNode;
            }
        }

        private void unlink(Node x) {
            final Node next = x.next;
            final Node prev = x.prev;

            if (prev == null) {
                first = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                last = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }
        }
    }


    @Test
    void test1() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4
    }

    @Test
    void test2() {
        runTest(
                asList("LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"),
                asList(asList(2), asList(1, 0), asList(2, 2), asList(1), asList(3, 3), asList(2), asList(4, 4),
                        asList(1),
                        asList(3),
                        asList(4)),
                asList(null, null, null, 0, null, -1, null, -1, 3, 4)
        );
    }

    private void runTest(List<String> ops, List<List<Integer>> inputs, List<Integer> expected) {
        LRUCache cache = null;
        for (int i = 0; i < ops.size(); i++) {
            if (ops.get(i).equals("LRUCache")) {
                cache = new LRUCache(inputs.get(i).get(0));
            } else if (ops.get(i).equals("put")) {
                int k = inputs.get(i).get(0);
                int v = inputs.get(i).get(1);
                cache.put(k, v);
                System.out.println("set " + k + " = "  + v);
            } else if (ops.get(i).equals("get")) {
                int k = inputs.get(i).get(0);
                int res = cache.get(k);
                System.out.println("get " + k);
                assertEquals(expected.get(i), res, "step " + i);
            }
            System.out.println(cache.map.keySet().toString());
            System.out.println();
        }
    }
}
