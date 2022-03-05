package leetcode.algo.dp.maxnumNeigbors;

import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/problems/delete-and-earn/
public class DeleteAndEarn740 {

    // 转换成打家劫舍问题 198
    // 简单的想法是 通过转换 将nums 转换成 array 这个array以index代表value，index上的值代表value出现的次数
    // 然后就是打家劫舍问题，因为相同的值不能被取

    // time O(n +k)
    // space O(n + k)
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] array = new int[max + 1];
        for (int num : nums) {
            array[num]++;
        }
        return robber(array);
    }

    int robber(int[] array) {
        int[] dp = new int[array.length];
        dp[0] = array[0];
        dp[1] = array[1] * 1;

        for (int i = 2; i < array.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + array[i] * i);
        }
        return dp[array.length - 1];
    }

    // time O(n +k)
    // space O(n + k)
    class TopDown {
        public int deleteAndEarn(int[] nums) {
            int[] buckets = new int[10001];
            for (int num : nums) {
                buckets[num] += num;
            }

            int[] dp = new int[10001];
            dp[0] = buckets[0];
            dp[1] = buckets[1];
            for (int i = 2; i < buckets.length; i++) {
                dp[i] = Math.max(buckets[i] + dp[i - 2], dp[i - 1]);
            }
            return dp[10000];
        }
    }

    // time O(n +k)
    // space O(n + k)
    class TopDownOfficial {
        class Solution {
            private Map<Integer, Integer> points = new HashMap<>();
            private Map<Integer, Integer> cache = new HashMap<>();

            private int maxPoints(int num) {
                // Check for base cases
                if (num == 0) {
                    return 0;
                }

                if (num == 1) {
                    return points.getOrDefault(1, 0);
                }

                if (cache.containsKey(num)) {
                    return cache.get(num);
                }

                // Apply recurrence relation
                int gain = points.getOrDefault(num, 0);
                cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain));
                return cache.get(num);
            }

            public int deleteAndEarn(int[] nums) {
                int maxNumber = 0;

                // Precompute how many points we gain from taking an element
                for (int num : nums) {
                    points.put(num, points.getOrDefault(num, 0) + num);
                    maxNumber = Math.max(maxNumber, num);
                }

                return maxPoints(maxNumber);
            }
        }
    }

}
