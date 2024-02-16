package code.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
Simplify unix path:

"/a/./b/../../c/" -> "/c

*/
class SimplifyPath {
    public String simplifyPath(String path) {
        String[] parts = path.split("/");
        List<String> dirs = new ArrayList<>();
        for (String str : parts) {
            if (str.equals(".")) {
                // ignore
            } else if (str.equals("..")) {
                if (dirs.size() > 0) {
                    dirs.remove(dirs.size() - 1);
                }
            } else if (!str.isEmpty()) {
                dirs.add(str);
            }
        }
        return "/" + String.join("/", dirs);
    }

    @Test
    void test1() {
        assertEquals("/c", simplifyPath("/a/./b/../../c/"));
    }

    @Test
    void test2() {
        assertEquals("/", simplifyPath("/../"));
    }
}
