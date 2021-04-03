package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

//https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
public class CountTriplets1442 {
    // a==b <--> xorsum(i, j - 1) == xorsum(j, k) <-> xorsum(i, j - 1) xor xorsum(j,
    // k) == 0<-> xorsum(i, k) == 0
    class Classsic {
        public int countTriplets(int[] arr) {
            int n = arr.length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                int xorsum = 0;
                for (int k = i; k < n; ++k) {
                    xorsum ^= arr[k];
                    if (xorsum == 0) {
                        ans += k - i;
                    }
                }
            }
            return ans;
        }
    }

    // 0=xorsum(i,k)=xorsum(0,i−1)∧xorsum(0,k) <==> presum(k)=presum(i−1)
    // (k - i1) +(k - i2) + (k - i3) +.... + (k - it) = t*k - sum(i1+....it)
    class Prefix {
        public int countTriplets(int[] arr) {
            int n = arr.length;
            // 第一个哈希表
            // 其中有初始边界值 (0, 1)，表示异或和为 0（左边界，一个数都不选择）出现了 1 次
            Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
            freq.put(0, 1);
            // 第二个哈希表
            // 其中有初始边界值 (0, 0)，表示异或和为 0（左边界，一个数都不选择）对应的 i 值为 0
            Map<Integer, Integer> idsum = new HashMap<Integer, Integer>();
            idsum.put(0, 0);
            int xorsum = 0, ans = 0;
            // 枚举 k
            for (int k = 0; k < n; ++k) {
                // 计算前缀异或和
                xorsum ^= arr[k];
                // 如果这个前缀异或和之前出现过，那么就找到了一些满足要求的三元组
                if (freq.containsKey(xorsum)) {
                    // 对应了题解中的公式 t * k - sum(i_t)
                    ans += freq.get(xorsum) * k - idsum.get(xorsum);
                }
                // 更新前缀异或和的出现次数
                freq.put(xorsum, freq.getOrDefault(xorsum, 0) + 1);
                // 更新前缀异或和出现位置的下标之和
                // 注意 i-1 和 i 的关系，所以这里要额外增加 1
                idsum.put(xorsum, idsum.getOrDefault(xorsum, 0) + k + 1);
            }
            return ans;
        }
    }
}
