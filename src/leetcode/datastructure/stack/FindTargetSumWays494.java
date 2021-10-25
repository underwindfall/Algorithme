package leetcode.datastructure.stack;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// https://leetcode-cn.com/problems/target-sum/
public class FindTargetSumWays494 {

    // time O(N*SUM)
    // espace O(N*SUM)
    // prefix sum理论
    // DP 问题首先找到公式 dp[i][j] = dp[i - 1][j - nums[i]]+ dp[i - 1][j + nums[i]]
    // 这个可以解释为 i 是当前的在数组重的index j是sum值 dp[i][j]是当前节点的target 为j的解
    // https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/
    class DP {
        // public int findTargetSumWays(int[] nums, int S) {
        //     int sum = 0;
        //     for (final int num : nums)
        //       sum += num;
        //     if (sum < S) return 0;
        //     final int kOffset = sum;
        //     final int kMaxN = sum * 2 + 1;
        //     int[] ways = new int[kMaxN];
        //     ways[kOffset] = 1;
        //     for (final int num : nums) {      
        //       int[] tmp = new int[kMaxN];      
        //       for (int i = num; i < kMaxN - num; ++i) {
        //         tmp[i + num] += ways[i];
        //         tmp[i - num] += ways[i];
        //       }
        //       ways = tmp;
        //     }
        //     return ways[S + kOffset];
        //   }
        public int findTargetSumWays(int[] nums, int s) {
            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            // 绝对值范围超过了sum的绝对值范围则无法得到
            if (Math.abs(s) > Math.abs(sum))
                return 0;

            int len = nums.length;
            // - 0 +
            int t = sum * 2 + 1;
            int[][] dp = new int[len][t];
            // 初始化
            if (nums[0] == 0) {
                dp[0][sum] = 2;
            } else {
                dp[0][sum + nums[0]] = 1;
                dp[0][sum - nums[0]] = 1;
            }

            for (int i = 1; i < len; i++) {
                for (int j = 0; j < t; j++) {
                    // 边界
                    int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                    int r = (j + nums[i]) < t ? j + nums[i] : 0;
                    dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
                }
            }
            return dp[len - 1][sum + s];
        }

    }

    class DFSMemo {
        public int findTargetSumWays(int[] nums, int t) {
            Map<String, Integer> map = new HashMap<>();
            return dfs(nums, t, 0, 0, map);
        }

        int dfs(int[] nums, int i, int sum, int S, Map<String, Integer> memo) {
            String key = i + "_" + sum;
            if (memo.containsKey(key))
                return memo.get(key);
            if (i == nums.length) {
                memo.put(key, sum == S ? 1 : 0);
                return memo.get(key);
            }
            int left = dfs(nums, i + 1, sum - nums[i], S, memo);
            int right = dfs(nums, i + 1, sum + nums[i], S, memo);
            memo.put(key, left + right);
            return memo.get(key);
        }
    }

    // time O(2^N)
    // espace O(N)
    // 思路是递归调用
    // 每一个节点的值对应 preSum +/- 当前这个节点
    // 当 nums 数组循环到底 并且剩余值等于S时候 记录++
    class DFS {
        int count = 0;

        public int findTargetSumWays(int[] nums, int S) {
            dfs(nums, 0, 0, S);
            return count;
        }

        void dfs(int[] nums, int i, int sum, int S) {
            if (i == nums.length) {
                if (sum == S) {
                    count++;
                } else {
                    return;
                }
            } else {
                dfs(nums, i + 1, sum + nums[i], S);
                dfs(nums, i + 1, sum - nums[i], S);
            }
        }
    }

    // time O(2^N)
    // espace O(N)
    class BFS {
        public int findTargetSumWays(int[] nums, int S) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(S);
            int level = 0;
            int res = 0;
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    int remain = queue.poll();
                    if (level == nums.length) {
                        if (remain == 0) {
                            res++;
                        }
                    } else {
                        queue.offer(remain - nums[level]);
                        queue.offer(remain + nums[level]);
                    }
                }
                level++;
            }
            return res;
        }
    }

}
