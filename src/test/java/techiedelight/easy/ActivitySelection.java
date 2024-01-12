package techiedelight.easy;

/*

Given a set of activities, along with the starting and finishing time of each activity, find the maximum number of activities performed by a single person assuming that a person can only work on a single activity at a time.

Input : [(1, 4), (3, 5), (0, 6), (5, 7), (3, 8), (5, 9), (6, 10), (8, 11), (8, 12), (2, 13), (12, 14)]
Output: {(1, 4), (5, 7), (8, 11), (12, 14)}

Input : [(3, 7), (1, 3), (2, 9), (2, 7), (1, 2), (7, 8)]
Output: {(1, 3), (3, 7), (7, 8)} or {(1, 2), (3, 7), (7, 8)} or {(1, 2), (2, 7), (7, 8)}

*/

import static org.junit.jupiter.api.Assertions.assertEquals;
import static techiedelight.Activity.a;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import techiedelight.Activity;

class ActivitySelection
{
    public static Set<Activity> selectActivity(List<Activity> activities) {
        activities = activities.stream()
                .sorted(Comparator.comparingInt(Activity::getFinish))
                .collect(Collectors.toList());

        Set<Activity> selectedActivities = new HashSet<>();
        Activity lastSelected = null;

        for (Activity current : activities) {
            if (lastSelected == null || current.getStart() >= lastSelected.getFinish()) {
                selectedActivities.add(current);
                lastSelected = current;
            }
        }

        return selectedActivities;
    }

    @Test
    void test1() {
        assertEquals(
                Set.of(a(1, 7), a(7, 13)),
                selectActivity(List.of(a(2, 12), a(1, 7), a(7, 13)))
        );
    }

}
