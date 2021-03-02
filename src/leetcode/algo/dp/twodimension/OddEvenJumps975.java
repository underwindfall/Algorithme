package leetcode.algo.dp.twodimension;

import java.util.Map;
import java.util.TreeMap;

public class OddEvenJumps975 {
    public int oddEvenJumps(int[] A) {
        int n = A.length, res = 1;
        // 上升跳
        boolean[] higher = new boolean[n];
        // 下降跳
        boolean[] lower = new boolean[n];
        higher[n - 1] = lower[n - 1] = true;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]), lo = map.floorEntry(A[i]);
            // 要去上 上升跳 == 之前 下降跳的最大元素
            if (hi != null)
                higher[i] = lower[(int) hi.getValue()];
            // 要去下 下降跳== 之前 上升跳的最小元素
            if (lo != null)
                lower[i] = higher[(int) lo.getValue()];
            //只记录开始上升跳的值 因为刚开始的第一个数字是不下降跳的    
            if (higher[i])
                res++;
            map.put(A[i], i);
        }
        return res;
    }
}
