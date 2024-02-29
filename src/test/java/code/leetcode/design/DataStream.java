package code.leetcode.design;

import java.util.LinkedList;

class DataStream {
    LinkedList<Integer> window = new LinkedList<>();
    int size;
    int value;
    int valueCount = 0;

    public DataStream(int value, int k) {
        this.size = k;
        this.value = value;
    }
    
    public boolean consec(int num) {
        window.addLast(num);
        if (num == value) {
            valueCount++;
        }
        if (window.size() > size) {
            int removed = window.removeFirst();
            if (removed == value) {
                valueCount--;
            }
        }
        return valueCount == size;
    }
}
