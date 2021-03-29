package leetcode.algo.dp.prefix;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/count-number-of-nice-subarrays/
public class CountNiceNumber1248 {
    // prefix 通过计算前i，j的奇数的和
    // 如果当前的奇数个数超过k 就count++ 否则继续

    class Prefix {
        public int numberOfSubarrays(int[] nums, int k) {
            int[] helper = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                helper[i + 1] = helper[i] + (nums[i] & 1);
            }
            // key - > prefix sum odd 值
            // value -> odd 值次数 个数
            Map<Integer, Integer> map = new HashMap<>();
            int ans = 0;
            for (int value : helper) {
                ans += map.getOrDefault(value - k, 0);
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
            return ans;
        }
    }

    // time O(N)
    // espace O(1)
    class SlidingWindow {
        // 滑动窗口方法
        public int numberOfSubarrays(int[] nums, int k) {
            int left = 0, right = 0, oddCnt = 0, res = 0;

            while (right < nums.length) {
                // 右指针先走，每遇到一个奇数则 oddCnt++。
                if (nums[right++] % 2 == 1) {
                    oddCnt++;
                }
                // 若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
                if (oddCnt == k) {
                    // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                    // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                    int tmp = right;
                    while (right < nums.length && nums[right] % 2 == 0) {
                        // 扩展到最右边的偶数最后一位
                        right++;
                    }
                    int rightEvenCnt = right - tmp;
                    // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                    int leftEvenCnt = 0;
                    while (nums[left] % 2 == 0) {
                        left++;
                        leftEvenCnt++;
                    }

                    // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                    // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                    // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                    // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                    // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                    res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                    // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                    left++;
                    oddCnt--;
                }
            }
            return res;
        }
    }
}
