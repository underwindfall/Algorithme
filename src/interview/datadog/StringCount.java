package interview.datadog;

import java.util.HashMap;
import java.util.Map;

public class StringCount {
    /**
     * given a string, find the total number of repetitions. Repetition means if a
     * word appear more than once, and repetition number is the times minus one.
     * for example: The sun is the largest object in the solar system. It is the
     * only star.
     * "the" apprears 4 times, "is" appears 2 time so return
     * valu‍‍‌‍‌‌‌‌‍‍‌‍‌‍‍‍‍‍‌‌e is 3+1 = 4
     */

    public static void main(String[] args) {
        String input = "The sun is the largest object in the solar system. It is the only star";
        System.out.println(findCount(input, "the"));
    }

    static int findCount(String input, String target) {
        if (input.length() == 0)
            return 0;
        Map<String, Integer> map = new HashMap<>();
        for (String s : input.split(" ")) {
            map.put(s.toLowerCase(), map.getOrDefault(s.toLowerCase(), 0) + 1);
        }

        if (map.containsKey(target.toLowerCase())) {
            return map.get(target);
        } else {
            return -1;
        }
    }

}
