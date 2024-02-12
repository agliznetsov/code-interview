package leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RestoreIPAdresses {

    private List<String> ips = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        split(s, 1, 0);
        return ips;
    }

    private void split(String s, int start, int dots) {
        if (dots == 3) {
            checkIP(s);
        } else {
            for (int i = 0; i < 3; i++) {
                int n = start + i;
                if (n < s.length()) {
                    String a = s.substring(0, n);
                    String b = s.substring(n);
                    split(a + "." + b, n + 2, dots + 1);
                }
            }
        }
    }

    private void checkIP(String ip) {
        String[] parts = ip.split("\\.");
        for(String part : parts) {
            if (part.length() > 3) {
                return;
            }
            if (part.startsWith("0") && part.length() > 1) {
                return;
            }
            if (Integer.parseInt(part) > 255) {
                return;
            }
        }
        ips.add(ip);
        System.out.println(ip);
    }

    @Test
    void test1() {
        restoreIpAddresses("12345");
    }

    @Test
    void test2() {
        assertEquals(List.of("255.255.11.135", "255.255.111.35"), restoreIpAddresses("25525511135"));
    }

    @Test
    void test3() {
        assertEquals(List.of("1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"), restoreIpAddresses("101023"));
    }

    @Test
    void test4() {
        restoreIpAddresses("0279245587303");
    }
}
