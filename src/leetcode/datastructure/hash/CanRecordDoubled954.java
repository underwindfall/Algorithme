package leetcode.datastructure.hash;

import java.util.Map;
import java.util.TreeMap;

//https://leetcode-cn.com/problems/array-of-doubled-pairs/
public class CanRecordDoubled954 {
    /**
     * 1. Count all numbers.
     * 2. Loop all numbers on the order of its absolute.
     *    We have counter[x] of x, so we need the same amount of 2x wo match them.
     *    If c[x] > c[2 * x], then we return false
     *    If c[x] <= c[2 * x], then we do c[2 * x] -= c[x] to remove matched 2x
     */
    //time O(n)
    //space O(n)
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> count = new TreeMap<>();
        for (int i : arr) {
            count.put(i, count.getOrDefault(i, 0) + 1);
        }
        for (int x: count.keySet()) {
            if (count.get(x) == 0) continue;
            //x < 0 时候 去的是 -x/2
            int want = x < 0 ? x / 2 : x * 2;
            //没有根
            if (x < 0 && x % 2 != 0 ) {
                return false;
            }
            //如果want的个数小于能生成的个数
            if (count.getOrDefault(want, 0) < count.get(x)) {
                return false;
            }

            count.put(want, count.get(want) - count.get(x));
        }
        return true;
    }
}
