package code.leetcode.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Test;

class UndergroundSystem {

    static class CheckIn {
        String stationName;
        int time;

        public CheckIn(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }

    private HashMap<Integer, CheckIn> checkIns = new HashMap<>();
    private HashMap<String,List<Integer>> routes = new HashMap<>();

    public void checkIn(int id, String stationName, int time) {
        checkIns.put(id, new CheckIn(stationName, time));
    }
    
    public void checkOut(int id, String stationName, int time) {
        CheckIn checkIn = checkIns.get(id);
        String route = checkIn.stationName + "-" + stationName;
        int travelTime = time - checkIn.time;
        routes.computeIfAbsent(route, it -> new ArrayList<>()).add(travelTime);
        checkIns.remove(id);

    }
    
    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "-" + endStation;
        List<Integer> times = routes.get(route);
        double totalTime = times.stream().mapToInt(it -> it).sum();
        return totalTime / times.size();
    }


    @Test
    void test() {
        UndergroundSystem us = new UndergroundSystem();
        us.checkIn(1, "A", 0);
        us.checkIn(2, "B", 1);
        us.checkOut(1, "C", 5);
        us.checkOut(2, "D", 10);

        assertEquals(5, us.getAverageTime("A", "C"));
        assertEquals(9, us.getAverageTime("B", "D"));
    }
}
