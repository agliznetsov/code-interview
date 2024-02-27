package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class SnapshotArrayTest {
    class SnapshotArray {
        Map<Integer, List<int[]>> values; // snapshotId, value
        int snapshotId = 0;

        public SnapshotArray(int length) {
            values = new HashMap<>(length);
        }

        public void set(int index, int val) {
            List<int[]> snapshots = values.get(index);
            if (snapshots == null) {
                snapshots = new ArrayList<>();
                snapshots.add(new int[] {snapshotId, val});
                values.put(index, snapshots);
            } else {
                int[] lastSnapshot = snapshots.get(snapshots.size() - 1);
                if (lastSnapshot[0] == snapshotId) {
                    lastSnapshot[1] = val;
                } else {
                    snapshots.add(new int[] {snapshotId, val});
                }
            }
        }

        public int snap() {
            return snapshotId++;
        }

        public int get(int index, int snap_id) {
            List<int[]> snapshots = values.get(index);
            if (snapshots != null) {
                return binarySearch(snapshots, snap_id);
            }
            return 0;
        }

        private int binarySearch(List<int[]> list, int target) {
            int left = 0;
            int right = list.size() - 1;
            int index = 0;

            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (list.get(mid)[0] <= target) {
                    index = mid;
                    left = mid + 1; // Move to the right half
                } else {
                    right = mid - 1; // Move to the left half
                }
            }
            int[] snap = list.get(index);
            if (snap[0] <= target) {
                return snap[1];
            } else {
                return 0;
            }
        }
    }

    @Test
    void test() {
        SnapshotArray array = new SnapshotArray(2);
        array.set(1, 10);
        array.set(1, 11);
        array.snap();
        array.snap();
        array.snap();
        array.set(1, 20);
        array.snap();
        array.snap();
        array.snap();
        int snapId = array.snap();

        assertEquals(0, array.get(0, snapId));
        assertEquals(20, array.get(1, snapId));
        assertEquals(11, array.get(1, 0));
        assertEquals(11, array.get(1, 1));
    }

    @Test
    void test2() {
        SnapshotArray array = new SnapshotArray(2);
        array.snap();
        array.set(1, 5);
        assertEquals(0, array.get(1, 0));
        assertEquals(5, array.get(1, 1));
    }
}
