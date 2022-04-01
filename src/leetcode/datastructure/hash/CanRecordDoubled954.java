package leetcode.datastructure.hash;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/array-of-doubled-pairs/
public class CanRecordDoubled954 {
    /**
     * 1. Count all numbers. 2. Loop all numbers on the order of its absolute. We have counter[x] of
     * x, so we need the same amount of 2x wo match them. If c[x] > c[2 * x], then we return false
     * If c[x] <= c[2 * x], then we do c[2 * x] -= c[x] to remove matched 2x
     */
    // time O(n)
    // space O(n)
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int x : arr) {
            count.put(x, count.getOrDefault(x, 0) + 1);
        }
        // B = A as Integer[], sorted by absolute value
        Integer[] B = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            B[i] = arr[i];
        }
        Arrays.sort(B, Comparator.comparingInt(Math::abs));

        for (int x : B) {
            // If this can't be consumed, skip
            if (count.get(x) == 0)
                continue;
            // If this doesn't have a doubled partner, the answer is false
            if (count.getOrDefault(2 * x, 0) <= 0)
                return false;
            // Write x, 2*x
            count.put(x, count.get(x) - 1);
            count.put(2 * x, count.get(2 * x) - 1);
        }
        return true;
    }
}
