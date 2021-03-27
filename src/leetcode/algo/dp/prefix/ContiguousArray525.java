package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/contiguous-array
public class ContiguousArray525 {

    // time O(N)
    // epsace O(N)
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        // 下标 0 之前的位置是 -1
        map.put(0, -1);

        int res = 0;
        int presum = 0;
        // 把0看成-1
        for (int i = 0; i < len; i++) {
            // 把 0 视为 -1，pre 是先加了，所以后面计算的时候是 i - map.get(preSum)，注意下标 + 1 和不加 1 的差别
            if (nums[i] == 1) {
                presum += 1;
            } else {
                presum -= 1;
            }
            // 含0 -> +1/-1 -> 记录最大长度
            if (map.containsKey(presum)) {
                res = Math.max(res, i - map.get(presum));
            } else {
                // 只记录这个数字第 1 次出现的下标
                map.put(presum, i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new ContiguousArray525().findMaxLength(new int[] { 1, 1 }));
    }
}
