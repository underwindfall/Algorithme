package leetcode.algo.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/hand-of-straights/
public class IsNStraightHande846 {
    // time O(n * logn)
    // space O(n)
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0)
            return false;
        Arrays.sort(hand);
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : hand) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        for (int x : hand) {
            if (!count.containsKey(x)) {
                continue;
            }
            for (int j = 0; j < groupSize; j++) {
                int num = x + j;
                if (!count.containsKey(num)) {
                    return false;
                }
                count.put(num, count.get(num) - 1);

                if (count.get(num) == 0) {
                    count.remove(num);
                }
            }
        }
        return true;
    }
}
