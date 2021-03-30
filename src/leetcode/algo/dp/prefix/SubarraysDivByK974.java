package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
public class SubarraysDivByK974 {
    // 用p[j]/Mod K 作为key 次数作为value
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        int presum = 0;

        for (int i = 0; i < A.length; i++) {
            presum += A[i];
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (presum % K + K) % K;
            int same = map.getOrDefault(modulus, 0);
            count += same;

            map.put(modulus, same + 1);
        }

        return count;
    }

    public static void main(String[] args) { 
        System.out.println((-3 % 4 + 4) % 4);
    }
}
