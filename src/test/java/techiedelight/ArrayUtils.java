package techiedelight;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayUtils {
    public static List<Integer> toList(int[] nums) {
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }
}
