package code.techiedelight.medium;
/*

Consider an event where a log register is maintained containing the guest's arrival and departure times. Given an array of arrival and departure times from entries in the log register, find the first occurrence when maximum guests were present in the event.

Input:

arrival = [1, 2, 4, 7, 8, 12]
departure = [2, 7, 8, 12, 10, 15]

Output: 7

Explanation: Maximum number of guests is 3, present at time 7 as shown below:

https://techiedelight.com/practice/images/Maximum-Overlapping-Interval.png


Note: If an arrival and departure event coincides, the arrival time is preferred over the departure time.

*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

class OverlappingIntervals {
    public static int findMaxGuests(int[] arrival, int[] departure) {
        var events = new ArrayList<Event>();
        Arrays.stream(arrival).mapToObj(it -> new Event(it, 1)).forEach(events::add);
        Arrays.stream(departure).mapToObj(it -> new Event(it, -1)).forEach(events::add);
        events.sort(Comparator.comparing(Event::getTime).thenComparing(Event::getIncrementReversed));

        int maxSum = 0;
        int sum = 0;
        int time = 0;
        for (var e : events) {
            sum += e.increment;
            if (sum > maxSum) {
                maxSum = sum;
                time = e.time;
            }
        }
        return time;
    }

    public static class Event {
        int time;
        int increment;

        public int getTime() {
            return time;
        }

        public int getIncrementReversed() {
            return increment * -1;
        }

        public Event(int time, int increment) {
            this.time = time;
            this.increment = increment;
        }
    }

	@Test
    void test() {
        assertEquals(7, findMaxGuests(new int[] {1, 2, 4, 7, 8, 12}, new int[] {2, 7, 8, 12, 10, 15}));
    }
}
