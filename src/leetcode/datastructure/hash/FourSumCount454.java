package leetcode.datastructure.hash;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/4sum-ii/
public class FourSumCount454 {
    // 思路 先算前两数组的和，并用map统计， key作为两个数的和，value作为次数
    // C与D的做负差值，如果map存在这样的数，就以为着4个数字和为0
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> groupAB = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int key = A[i] + B[j];
                groupAB.put(key, groupAB.getOrDefault(key, 0) + 1);
            }
        }
        int count = 0;
        for (int c : C) {
            for (int d : D) {
                int minusCD = -c - d;
                if (groupAB.containsKey(minusCD)) {
                    count += groupAB.get(minusCD);
                }
            }
        }
        return count;
    }
}
